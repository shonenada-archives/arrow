(ns arrow.utils.common_test
  (:use [clojure.test])
  (:require [arrow.utils.common :as u]))

(deftest test-bytes?
  (testing "test bytes?"
    (let [integer 2333 
          string "this is a string"
          is-bytes (.getBytes string)]
      (is (u/bytes? is-bytes))
      (is (not (u/bytes? integer)))
      (is (not (u/bytes? string))))))

(deftest test->int
  (testing "test ->int"
    (let [int-str "123"
          int-int 123
          answer 123]
      (is (= (u/->int int-str) answer))
      (is (= (u/->int int-int) answer)))))
