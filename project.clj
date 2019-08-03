(defproject isahn "0.1.0-SNAPSHOT"
  :description "ISAHN check news stories for Hacker News"
  :url "http://example.com/FIXME"
  :micense {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 ; Clojure
                 [org.clojure/clojure "1.10.0"]
                 ; Get config from .env
                 [yogthos/config "1.1.4"]
                 ; Client HTTP
                 [clj-http "3.10.0"]
                 ; Parse JSON
                 [cheshire "5.8.1"]
                ]
  :plugins [
            ; Dev Tools
            [lein-auto "0.1.3"]
            [lein-kibit "0.1.7"]
            [lein-cljfmt "0.6.4"]
            ]
  :main ^:skip-aot isahn.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
