(ns isahn.core
  (:require
   [clj-http.client :as client]
   [cheshire.core :refer :all]
   )
  (:gen-class))

" URLs from API Hacker News "
(def all_stories "https://hacker-news.firebaseio.com/v0/topstories.json")

(defn one_story
  [id]
  (str "https://hacker-news.firebaseio.com/v0/item/" id ".json"))

" Min score "
(def min_score 600)

(def now (quot (System/currentTimeMillis) 1000))
(def unixtime24h 86400)

(defn -main
  "Main execution"
  [& args]
  " Get all ids stories"
  (def ids_stories (parse-string (:body (client/get all_stories {:accept :json}))))

  " Get all API urls stories "
  (def urls_stories (map #(one_story %) ids_stories))

  " Get all data stories "
  (def stories (map #(parse-string (:body (client/get % {:accept :json}))) urls_stories))

  " Filter created less 24h "
  (def stories_24h (filter #(> (get-in % ["time"]) (- now unixtime24h)) stories))

  " Filter with score min_score "
  (def stories_top (filter #(> (get-in % ["score"]) min_score) stories_24h))

  (prn stories_top)
  )
