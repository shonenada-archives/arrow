(ns arrow.config
  (:use [environ.core :refer [env]]
        [arrow.utils.common :refer [->int]]))

(defonce web-host (env :arrow-web-host "0.0.0.0"))
(defonce web-port (->int (env :arrow-web-port "3000")))
(defonce secret-key (env :arrow-secret-key))

; database config
(defonce db-type (env :arrow-db-type))
(defonce db-host (env :arrow-db-host))
(defonce db-port (env :arrow-db-port))
(defonce db-name (env :arrow-db-name))
(defonce db-username (env :arrow-db-username))
(defonce db-password (env :arrow-db-password))

(cond
  (= db-type "mysql") (defonce db-subname (str "//" db-host ":" db-port "/" db-name))
  :else (db-name))

(defonce db-params
  {:subprotocol db-type
   :subname db-subname
   :db db-name
   :user db-username
   :password db-password})
