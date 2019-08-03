(ns isahn.core
  (:require
   [config.core :refer [env]]
   [clojure.java.io :as io]
   [clj-http.client :as client]
   [cheshire.core :refer :all])
  (:gen-class))

" VARIABLES "

" URL send for Telegram "
(def url_telegram_send (str "https://api.telegram.org/" (:bot_token env) "/sendMessage"))
" URLs from API Hacker News "
(def url_all_stories "https://hacker-news.firebaseio.com/v0/topstories.json")
" Min score "
(def min_score (:min_score env))
" Now unixtime "
(def now (quot (System/currentTimeMillis) 1000))
" 24h in unixtime "
(def unixtime24h 86400)
" Now - 24h "
(def min_time (- now unixtime24h))
" Path file save history "
(def path_history "isahn_history.json")
(def history (if (.exists (io/file path_history)) (parse-string (slurp (io/file path_history)) true) (str "")))
(def history_ids (map :id history))

" FUNCTIONS "

(defn one_story
  " Get url from item "
  [id]
  (str "https://hacker-news.firebaseio.com/v0/item/" id ".json"))

(defn get_all_stories
  " Get all stories "
  [url_all_stories]
  " Get all ids stories"
  (def ids_stories (parse-string (:body (client/get url_all_stories {:accept :json}))))
  " Get all API urls stories "
  (def urls_stories (map one_story ids_stories))
  " Get all data stories "
  (map #(parse-string (:body (client/get % {:accept :json}))) urls_stories))

(defn lazy-contains? [col key]
  (some #{key} col))

(defn set-interval [callback ms]
  " Run function every ms "
  (future (while true (do (Thread/sleep ms) (callback)))))

(defn add_history
  " Add to file history news_stories "
  [news_stories]
  (def history_news_ids (concat history_ids (map #(get-in % ["id"]) news_stories)))
  (def history_all (map #(assoc {} :id %) (vec history_news_ids)))
  (with-open [w (clojure.java.io/writer path_history :append false)]
    (.write w (generate-string history_all)))
  )

(defn filter_stories
  " Filter stories by last 24h, remove histories and lower score "
  [stories]
  " Filter created less 24h "
  (def stories_24h (filter #(> (get-in % ["time"]) min_time) stories))

  " Filter history "
  (def stories_without_histories (filter #(not (lazy-contains? history_ids (get-in % ["id"]))) stories_24h))

  " Filter with score min_score "
  (filter #(> (get-in % ["score"]) min_score) stories_without_histories))

(defn send_stories_telegram
  [stories]
  " Send stories by Telegram Channel "
  (doall (iterate #((client/post url_telegram_send {:body (generate-string {:chat_id (:chat env)
                                                                            :text (str (get-in % ["title"]) ": " (get-in % ["url"]))
                                                                            :disable_notification true})
                                                    :content-type :json
                                                    :accept :json})) stories)))

(defn check_stories
  " Check news stories and send message to Telegram "
  []
  (def stories_top (filter_stories (get_all_stories url_all_stories)))
  (doall (add_history stories_top))
  (doall (send_stories_telegram stories_top)))

(defn -main
  "Main execution"
  []
  " Run first time "
  (check_stories)
  " Run every :run_every_miliseconds "
  (set-interval check_stories (:run_every_miliseconds env)))
