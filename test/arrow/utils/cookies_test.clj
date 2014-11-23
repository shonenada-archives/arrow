(ns arrow.utils.cookies_test
  (:use [arrorw.utils.cookies])
  (:require [arrow.config :as config]))

(deftest test->cookie
  (testing "test ->cookies"
    (let [cookie (->cookie "value")
          answer {:value "value" :path "/" :max-age config/cookie-age}]
      (is (= cookie answer)))))
