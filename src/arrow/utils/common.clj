(ns arrow.utils.common
  (:import [org.mindrot.jbcrypt BCrypt]))

(defn ->int [s]
  (try 
    (cond
      (string? s) (Integer/parseInt s)
      (instance? Integer s) (s)
      (instance? Long s) (.intValue ^Long s)
      :else nil)
    (catch Exception e
      nil)))

(defn now
  {:tag java.util.Date}
  []
  (java.util.Date.))

(defn gen-salt
  ([]
   (BCrypt/gensalt))
  ([size]
   (BCrypt/gensalt size)))

(defn hash-str
  [raw-str]
  (let [salt (gen-salt)]
   (BCrypt/hashpw raw-str salt)))

(defn chk-hash
  [raw hash-str]
  (BCrypt/checkpw raw hash-str))
