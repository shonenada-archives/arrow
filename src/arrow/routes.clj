(ns arrow.routes
  (:use [compojure.core])
  (:require [compojure.route :as route]
            [arrow.controllers.index :as index-controller]
            [arrow.controllers.user :as user-controller]))

(defn page-not-found []
  "Page not found.")

(defroutes app-routes
  (GET "/" [request] index-controller/index)

  (GET "/account" [request] user-controller/current-user)
  (POST "/account/signin" [request] user-controller/sign-in)
  (POST "/account/signup" [request] user-controller/sign-up)

  (route/resources "/")
  (route/not-found (page-not-found)))
