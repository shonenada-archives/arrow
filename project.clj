(defproject Arrow "0.1.0-SNAPSHOT"
  :description "Simple practise."
  :url "https://github.com/shonenada/arrow"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main arrow.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [compojure "1.0.1"]
                 [mysql/mysql-connector-java "5.1.31"]
                 [korma "0.4.0"]])
