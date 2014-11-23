(ns arrow.utils.common_test
  (:use [arrow.utils.common]))

(deftest test-bytes?
  (testing "test bytes?"
    (let [integer 2333 
          string ("this is a string")
          is-bytes (.getBytes string)]
      (is (bytes? is-bytes))
      (is (not (bytes? integer)))
      (is (not (bytes? string))))))

(deftest test->int
  (testing "test ->int"
    (let [int-str "123"
          int-int 123
          answer 123]
      (is (= (->int int-str) answer))
      (is (= (->int int-int) answer)))))
