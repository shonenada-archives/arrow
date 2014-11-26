(ns arrow.utils.cookies
  (:require [arrow.config :as config]
            [arrow.utils.cipher :as cipher :only [aes-encrypt]]))

(defn ->cookie [value]
  {:value value
   :path "/"
   :max-age config/cookie-age})

(defn gen-cookie [input]
  (-> input
      str
      (cipher/aes-encrypt config/cookie-key)
      ->cookie))

(defn get-cookie [req key-n]
  (let [key-name (name key-n)
        cookie-name (str config/cookie-prefix key-name)
        cookies (req :cookies)
        cookie (-> cookies (get cookie-name) :value)]
    (str (cipher/aes-decrypt cookie config/cookie-key))))

(defn add-cookies [req resp key-n value]
  (let [key-name (name key-n)
        cookie-name (str config/cookie-prefix key-name)
        cookie {cookie-name (gen-cookie value)}
        cookies (req :cookies)]
    (if-let [old-cookie (-> cookies (get cookie-name) :value)]
      (dissoc cookies cookie-name))
    (let [insert-cookies (conj cookies cookie)]
      (assoc resp :cookies insert-cookies))))

(defn delete-cookie [req resp key-name]
  (let [cookie-name (str config/cookie-prefix key-name)
        cookies (req :cookies)]
    (if-let [cookie (-> cookies (get cookie-name) :value)]
      (dissoc cookies cookie-name))
    (assoc resp :cookies cookies)))
