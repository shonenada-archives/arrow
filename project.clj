(defproject Arrow "0.1.0-SNAPSHOT"
  :description "Simple practise."
  :url "https://github.com/shonenada/arrow"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :main arrow.core
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [org.clojure/data.json "0.2.5"]
                 [org.clojure/java.jdbc "0.3.5"]
                 [org.clojure/tools.logging "0.3.0"]
                 [org.clojure/data.generators "0.1.2"]
                 [ring/ring-core "1.2.2"]
                 [ring/ring-jetty-adapter "1.2.2"]
                 [mysql/mysql-connector-java "5.1.31"]
                 [environ "0.5.0"]
                 [ring/ring-json "0.3.1"]
                 [compojure "1.0.1"]
                 [org.mindrot/jbcrypt "0.3m"]
                 [korma "0.4.0"]])
