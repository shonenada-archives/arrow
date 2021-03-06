(ns arrow.routes
  (:use [compojure.core])
  (:require [compojure.route :as route]
            [arrow.controllers.index :as index-controller]
            [arrow.controllers.user :as user-controller]
            [arrow.controllers.letter :as letter-controller]))

(defn page-not-found []
  "Page not found.")

(defroutes app-routes
  (GET "/" [request] index-controller/index)

  (GET "/account" [request] user-controller/current-user)
  (POST "/account/signin" [request] user-controller/sign-in)
  (POST "/account/signup" [request] user-controller/sign-up)
  (POST "/account/signout" [request] user-controller/sign-out)

  (POST "/account/send" [request] letter-controller/send-letter)
  (GET "/account/letters" [request] letter-controller/get-letters)
  (GET "/account/inbox" [request] letter-controller/get-inbox)

  (route/resources "/")
  (route/not-found (page-not-found)))
