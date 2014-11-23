(ns arrow.models.user
  (:use [korma.core]
        [arrow.database])
  (:require [arrow.utils.common :as utils]
            [clojure.tools.logging :as log]))

(defonce ^:const db-user-normal 0)
(defonce ^:const db-user-inactive 1)
(defonce ^:const db-user-frozen 2)
(defonce ^:const db-user-deleted 3)

(defn get-by-id [id]
  (first (select users
                 (where {:id id}))))

(defn get-by-username [username]
  (first (select users (where {:username username}))))

(defn get-by-email [email]
  (first (select users (where {:email email}))))

(defn get-by-nickname [nickname]
  (first (select users (where {:nickname nickname}))))

(defn username-exist? [username]
  (if-let [user (get-by-username username)]
    (true)
    (false)))

(defn email-exist? [email]
  (if-let [user (get-by-email email)]
    (true)
    (false)))

(defn nickanme-exist? [nickname]
  (if-let [user (get-by-nickname nickname)]
    (true)
    (false)))

(defn create [user]
  (let [timestamp (utils/now)
        record (assoc user
                      :created timestamp
                      :state db-user-inactive)
        {id :generated_key} (insert users (values record))]
    (get-by-id id)))

(defn check-password [user raw-passwd]
  (utils/chk-hash raw-passwd (:password user)))

(defn set-token [user token]
  (update users
    (set-fields {:token token})
    (where {:id (:id user)})))

(defn chk-token [user token]
  (let [user-token {:token user}]
    (= user-token token)))
