(ns arrow.core
  (:use [ring.adapter.jetty]
        [arrow.routes])
  (:require [compojure.handler :as handler]))

(defn shot-arrow [& args]
  (def app (handler/api app-routes))
  (run-jetty app {:port 3000}))

(defn -main [& args]
  (shot-arrow))
