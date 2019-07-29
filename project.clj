(defproject isahn "0.1.0-SNAPSHOT"
  :description "ISAHN check news stories for Hacker News"
  :url "http://example.com/FIXME"
  :micense {:name "EPL-2.0 OR GPL-2.0-or-later WITH Classpath-exception-2.0"
            :url "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [
                 ; Clojure
                 [org.clojure/clojure "1.10.0"]
                 ; Client HTTP
                 [clj-http "3.10.0"]
                 ; Parse JSON
                 [cheshire "5.8.1"]
                 ]
  :main ^:skip-aot isahn.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
