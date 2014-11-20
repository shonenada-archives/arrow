(ns arrow.controllers.user
  (:use [arrow.utils.controller-helper])
  (:require [arrow.models.user :as user-model]
            [clojure.tools.logging :as log]
            [clojure.data.json :as json]
            [arrow.utils.common :as utils]))

(defn sign-up [request]
  (let [json-params (:json-params request)
        username (get json-params "username")
        password (get json-params "password")
        email (get json-params "email")
        nickname (get json-params "nickname")
        user {:username username
              :password (utils/hash-str password)
              :email email
              :nickname nickname}]
    (if (or (empty? username) (empty? password) (empty? email) (empty? nickname))
      (response {:succsss false :messages ["fields required"]})
      (if-let [user-by-username (user-model/get-by-username username)]
        (response {:success false :messages ["username exists"]})
        (if-let [user-by-nickname (user-model/get-by-nickname nickname)]
          (response {:success false :messages ["nickname exists"]})
          (if-let [user-by-email (user-model/get-by-email email)]
            (response {:success false :messages ["email exists"]})
            (let [created-user (user-model/create user)]
              (response {:success true :uid (get created-user :id)}))))))))

(defn sign-in [request]
  (let [json-params (:json-params request)
        username (get json-params "username")
        password (get json-params "password")]
    (if (or (empty? username) (empty? password))
      (response {:success false :messages ["fields required"]})
      (if-let [user (user-model/get-by-username username)]
        (if (user-model/check-password user password)
          (response {:success true :messages ["login success"]})
          (response {:success false :messages ["wrong username or password."]}))
        (response {:success false :messages ["wrong username or password."]})))))
