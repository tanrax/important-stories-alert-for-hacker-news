(defproject isahn "1.0.0"
  :description "ISAHN check news stories for Hacker News"
  :url "http://example.com/FIXME"
  :micense {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url  "https://github.com/tanrax/important-stories-alert-for-hacker-news"}
  :dependencies [
                 ;; Clojure
                 [org.clojure/clojure "1.10.0"]
                 ;; Get config from .env
                 [yogthos/config "1.1.4"]
                 ;; Client HTTP
                 [clj-http "3.10.0"]
                 ;; Parse JSON
                 [cheshire "5.8.1"]
                 ]
  :plugins [
            ;; Dev Tools
            ;;; Watch changes
            [lein-auto "0.1.3"]
            ;;; Check idiomatic bug
            [lein-kibit "0.1.7"]
            ;;; Check format
            [lein-cljfmt "0.6.4"]
            ;;; Generate documentation
            [lein-codox "0.10.7"]
            ]
  :main ^:skip-aot isahn.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
