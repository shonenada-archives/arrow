(ns arrow.utils.cipher_test
  (:use [clojure.test])
  (:require [arrow.utils.cipher :refer :all]))

(deftest test-encode-base64-bytes
  (testing "encode-base64-bytes"
    (let [input (.getBytes "123456789")
          answer "MTIzNDU2Nzg5"]
      (is (= (encode-base64-bytes input) answer)))))

(deftest test-encode-base64
  (testing "encode-base64"
    (let [input "123456789"
          answer "MTIzNDU2Nzg5"]
      (is (= (encode-base64 input) answer)))))

(deftest test-decode-base64-bytes
  (testing "decode-base64"
    (let [input "MTIzNDU2Nzg5"
          answer (.getBytes "123456789")]
      (is (= (String. (decode-base64-bytes input)) (String. answer))))))

(deftest test-decode-base64
  (testing "decode-base64"
    (let [input "MTIzNDU2Nzg5"
          answer "123456789"]
      (is (= (decode-base64 input) answer)))))

(deftest test-aes-cipher
  (testing "aes-cipher"
    (let [key "1234567890123456"
          input "123456789"
          encrypted (aes-encrypt input key)
          decrypted (aes-decrypt encrypted key)]
      (is (= decrypted input)))))
