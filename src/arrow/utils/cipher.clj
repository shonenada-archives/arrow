(ns arrow.utils.cipher
  (:require [arrow.utils.common :only [bytes?]])
  (:import [org.apache.commons.codec.binary Base64]
           [java.security MessageDigest SecureRandom]
           [javax.crypto Cipher KeyGenerator SecretKey]
           [javax.crypto.spec SecretKeySpec]))

(defn encode-base64-bytes [^bytes input]
  {:tag String}
  (Base64/encodeBase64String input))

(defn encode-base64 [^String input]
  (let [input (.getBytes input)]
    (encode-base64-bytes input)))

(defn decode-base64-bytes [^String input]
  {:tag bytes}
  (Base64/decodeBase64 input))

(defn decode-base64 [^String input]
  (let [output (decode-base64-bytes input)]
    (String. output)))

(defn sha-1<- [raw-str]
  (let [sha (MessageDigest/getInstance "SHA-1")]
    (.digest sha raw-str)))

(defn md5<- [raw-str]
  (let [md5 (MessageDigest/getInstance "MD5")]
    (.digest md5 raw-str)))

(defn get-raw-key [seed]
  (let [keygen (KeyGenerator/getInstance "AES")
        secret-random (SecureRandom/getInstance "SHA1PRNG")]
    (.setSeed secret-random (.getBytes seed "UTF-8"))
    (.init keygen 128 secret-random)
    (.. keygen generateKey getEncoded)))

(defn get-cipher [mode seed]
  (let [key-spec (SecretKeySpec. (get-raw-key seed) "AES")
        cipher (Cipher/getInstance "AES")]
    (.init cipher mode key-spec)
    cipher))

(defn aes-encrypt [plain-text key]
  (let [text-bytes (.getBytes plain-text "UTF-8")
        cipher (get-cipher Cipher/ENCRYPT_MODE key)]
    (encode-base64-bytes (.doFinal cipher text-bytes))))

(defn aes-decrypt [cipher-text key]
  (let [cipher (get-cipher Cipher/DECRYPT_MODE key)]
    (String. (.doFinal cipher (decode-base64-bytes cipher-text)))))
