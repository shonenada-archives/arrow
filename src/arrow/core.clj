(ns arrow.core
  (:use [ring.adapter.jetty]
        [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
        [arrow.routes])
  (:require [compojure.handler :as handler]
            [clojure.tools.logging :as log]
            [arrow.config :as config]))

;; middleware
(def app (handler/site (-> app-routes
                           wrap-json-response 
                           wrap-json-params)))

(defn shot-arrow [& args]
  (log/info (str "Arrow shot at port: " config/web-port))
  (run-jetty app {:port config/web-port}))

(defn -main [& args]
  (shot-arrow))
