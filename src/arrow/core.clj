(ns arrow.core
  (:use [ring.adapter.jetty]
        [ring.middleware.json :refer [wrap-json-params wrap-json-response]]
        [ring.middleware.session :only [wrap-session]]
        [ring.middleware.session.cookie :only [cookie-store]]
        [arrow.routes])
  (:require [compojure.handler :as handler]
            [clojure.tools.logging :as log]
            [arrow.config :as config]))

(defn wrap-cookie
  [handler]
  (wrap-session handler {:cookie-attrs {:secure true}
                         :cookie-name "arrow-cookie"
                         :store (cookie-store)}))

;; middleware
(def app (handler/site (-> app-routes
                           wrap-cookie
                           wrap-json-response 
                           wrap-json-params)))

(defn shot-arrow [& args]
  (log/info (str "Arrow shot at port: " config/web-port))
  (run-jetty app {:port config/web-port}))

(defn -main [& args]
  (shot-arrow))
