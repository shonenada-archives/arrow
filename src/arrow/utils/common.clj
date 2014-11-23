(ns arrow.utils.common
  (:require [clojure.data.generators :as dg :only [uuid]])
  (:import [org.mindrot.jbcrypt BCrypt]))

(defn bytes? [x]
  (= (Class/forName "[B")
     (.getClass x)))

(defn ->int [s]
  (try 
    (cond
      (string? s) (Integer/parseInt s)
      (instance? Integer s) (s)
      (instance? Long s) (.intValue ^Long s)
      :else nil)
    (catch Exception e
      nil)))

(defn ->long [s]
  (try
    (cond
      (string? s) (Long/parseLong s)
      (instance? Integer s) (s)
      (instance? Long s) (s)
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

(defn hash-pw
  [raw-str]
  (let [salt (gen-salt)]
   (BCrypt/hashpw raw-str salt)))

(defn chk-hash
  [raw hash-str]
  (BCrypt/checkpw raw hash-str))

(defn uuid []
  dg/uuid)
