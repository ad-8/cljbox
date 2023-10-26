(ns cljbox-test
  (:require [clojure.test :refer :all]
            [cljbox.math :refer :all]))

(deftest math
  (testing "add"
    (is (= 3 (add 1 2)))
    (is (= -3 (add -1 -2)))
    (is (= 0 (add 0 0)) "test adding zeros"))
  (testing "round-to"
    (let [PI 3.1415926535]
      (is (= 3.14 (round-to 2 PI)))
      (is (= 3.1 (round-to 1 PI)))
      (is (= 3.0 (round-to 0 PI)))
      (is (= 4.0 (round-to 0 3.5))))))

(run-tests)
