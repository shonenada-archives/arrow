(ns arrow.utils.controller_helper_test
  (:require [arrow.utils.controller-helper :as helper]))

(deftest test-200-response
  (testing "test 200 response"
    (let [resp helper/response "data"
          answer {:status 200
                  :headers {"Content-Type" "application/json;charset=utf-8"}
                  :body "data"}]
      (is (= resp answer)))))

(deftest test-403-response
  (testing "test 403 response"
    (let [resp helper/response "data" 403
          answer {:status 403
                  :headesr {"Content-Type" "application/json;charset=utf-8"}
                  :body "data"}]
      (is (= resp answer)))))
