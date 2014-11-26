(ns arrow.utils.cookies
  (:require [arrow.config :as config]
            [arrow.utils.cipher :as cipher :only [aes-encrypt]]))

(defn ->cookie [value]
  {:value value
   :path "/"
   :max-age config/cookie-age})

(defn expired-cookie []
  {:value (cipher/aes-encrypt "expired" config/cookie-key)
   :path "/"
   :max-age 0
   :expires "Thu, 01 Jan 1970 00:00:00 GMT"})

(defn gen-cookie [input]
  (-> input
      str
      (cipher/aes-encrypt config/cookie-key)
      ->cookie))

(defn ->cookie-name [key-name]
  (str config/cookie-prefix (name key-name)))

(defn get-cookie [req key-name]
  (let [cookie-name (->cookie-name key-name)
        cookies (req :cookies)]
    (if-let [cookie (-> cookies (get cookie-name) :value)]
      (str (cipher/aes-decrypt cookie config/cookie-key)))))

(defn **add-cookie [resp req key-name cookie-entry]
  (let [cookie-name (->cookie-name key-name)
        cookies (conj (req :cookies) (resp :cookies))]
    (assoc resp :cookies (conj cookies {cookie-name cookie-entry}))))

(defn add-cookies [resp req key-name value]
  (let [cookie-entry (gen-cookie value)]
    (**add-cookie resp req key-name cookie-entry)))

(defn delete-cookie [resp req key-name]
  (**add-cookie resp req key-name (expired-cookie)))
