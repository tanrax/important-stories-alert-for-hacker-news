(ns isahn.core
  (:require
   [config.core :refer [env]]
   [clojure.java.io :as io]
   [clj-http.client :as client]
   [cheshire.core :refer :all]
   )
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
  (def urls_stories (map #(one_story %) ids_stories))
  " Get all data stories "
  (map #(parse-string (:body (client/get % {:accept :json}))) urls_stories))

(defn filter_stories
  [stories]
  " Filter created less 24h "
  (def stories_24h (filter #(> (get-in % ["time"]) min_time) stories))

  " Filter with score min_score "
  (filter #(> (get-in % ["score"]) min_score) stories_24h))


(defn -main
  "Main execution"
  []
  ;; (def stories_top (filter_stories (get_all_stories url_all_stories)))
  (def history-file (parse-string (io/file path_history)))
  (prn (slurp data-file))
                                        ;https://stackoverflow.com/questions/7756909/in-clojure-1-3-how-to-read-and-write-a-file
  ;; (doall (iterate #((client/post url_telegram_send {:basic-auth ["user" "pass"]
  ;;                                      :body (generate-string {
  ;;                                                              :chat_id (:chat env) 
  ;;                                                              :text (str (get-in % ["title"]) ": " (get-in % ["url"]))
  ;;                                                              :disable_notification true
  ;;                                                              })
  ;;                                      :content-type :json
  ;;                                      :accept :json})) stories_top))
  )
