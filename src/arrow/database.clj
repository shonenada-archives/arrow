(ns arrow.database
  (:use [korma.core]
        [korma.db :only [defdb mysql]])
  (:require [arrow.config :as config]))

(defdb arrow-db (mysql config/db-params))

(declare users letters)

;; fields: :id :username :password :nickname :email :token :created :state
(defentity users
  (pk :id)
  (table :users)
  (database arrow-db))

(defentity letters
  (pk :id)
  (table :letters)
  (database arrow-db)
  (has-one users {:fk :to_uid})
  (belongs-to users {:fk :uid}))
