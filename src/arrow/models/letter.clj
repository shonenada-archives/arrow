(ns arrow.models.letter
  (:use [korma.core]
        [arrow.database])
  (:require [arrow.utils.common :as utils]))

(defonce ^:const db-letter-draft 0)
(defonce ^:const db-letter-cancel 1)
(defonce ^:const db-letter-sent 2)
(defonce ^:const db-letter-recieve 3)
(defonce ^:const db-letter-deleted 4)

(defn get-by-id [id]
  (first (select letters 
                 (where {:id id}))))

(defn create [letter]
  (let [timestamp (utils/now)
        record (assoc letter
                      :created timestamp
                      :state db-letter-sent)
        {id :generated_key} (insert letters (values record))]
    (get-by-id id)))
