(ns arrow.controllers.index
  (:use [arrow.utils.controller-helper]))


(defn index [request]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body "Welcome"})
