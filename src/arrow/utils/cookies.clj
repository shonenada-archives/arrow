(ns arrow.utils.cookies
  (:require [arrow.config :as config]
            [arrow.utils.cipher :only [aes-encrypt]]
            ))

(defn ->cookie [value]
  {:value value
   :path "/"
   :max-age config/cookie-age})

(defn gen-cookie [input]
  (-> input
      str
      aes-encrypt
      ->cookie))

(defn add-cookies [req resp key value]
  (let [cookie-name (str config/cookie-prefix key)
        cookie {cookie-name (gen-cookie value)}
        cookies (req :cookies)]
    (if-let [old-cookie (-> cookies (get cookie-name) :value)]
      (dissoc cookies cookie-name))
    (let [insert-cookies (conj cookies cookie)]
      (assoc resp :cookies insert-cookies))))

(defn delete-cookie [req resp key]
  (let [cookie-name (str config/cookie-prefix key)
        cookies (req :cookies)]
    (if-let [cookie (-> cookies (get cookie-name) :value)]
      (dissoc cookies cookie-name))
    (assoc resp :cookies cookies)))
