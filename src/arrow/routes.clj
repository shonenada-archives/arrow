(ns arrow.routes
  (:use [compojure.core])
  (:require [compojure.route :as route]
            [arrow.controllers.index :as index-controller]))

(defn page-not-found []
  "Page not found.")

(defroutes app-routes
  (GET "/" [request] index-controller/index)
  (route/resources "/")
  (route/not-found (page-not-found)))
