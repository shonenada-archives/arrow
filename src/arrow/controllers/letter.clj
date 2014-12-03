(ns arrow.controllers.letter
  (:use [arrow.utils.controller-helper])
  (:require [clojure.data.json :as json]
            [arrow.models.user :as user-model]
            [arrow.models.letter :as letter-model]
            [arrow.utils.cookies :as cookies]))

(defn send-letter [request]
  (let [json-params (:json-params request)
        current-username (cookies/get-cookie request "username")
        target-username (get json-params "send_to")
        content (get json-params "content")]
    (if-let [target-user (user-model/get-by-username target-username)]
      (if-let [current-user (user-model/get-by-username current-username)]
        (let [letter {:uid (current-user :id)
                      :to_id (target-user :id)
                      :content content}
              created-letter (letter-model/create letter)]
          (response {:success true}))
        (response nil))
      (response {:success false :messages ["Target username is not exists"]}))))
