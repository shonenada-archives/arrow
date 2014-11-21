(ns arrow.config
  (:use [environ.core :refer [env]]
        [arrow.utils.common :refer [->int]]))

(defonce cookie-age (* 24 30 60 60))
(defonce cookie-prefix "letters_")

(defonce web-host (env :arrow-web-host "0.0.0.0"))
(defonce web-port (->int (env :arrow-web-port "3000")))
(defonce secret-key (env :arrow-secret-key))
(defonce cookie-key (env :arrow-cookie-key))

; database config
(defonce db-type (env :arrow-db-type "mysql"))
(defonce db-host (env :arrow-db-host "127.0.0.1"))
(defonce db-port (env :arrow-db-port 3306))
(defonce db-name (env :arrow-db-name "arrow"))
(defonce db-username (env :arrow-db-username ""))
(defonce db-password (env :arrow-db-password ""))

(cond
  (= db-type "mysql") (defonce db-subname (str "//" db-host ":" db-port "/" db-name))
  :else (db-name))

(defonce db-params
  {:subprotocol db-type
   :subname db-subname
   :db db-name
   :user db-username
   :password db-password})
