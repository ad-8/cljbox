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
      (is (= 4.0 (round-to 0 3.5)))))
  (testing "mean"
    (is (= 0 (mean [])))
    (is (= 1/2 (mean [0 1])))
    (is (= 1 (mean [0 1 2]))))
  (testing "median"
    (is (= 0 (median [])))
    (is (= 1 (median [0 1 2])))
    (is (= 3/2 (median [0 1 2 3]))))
  (testing "standard-deviation"
    (is (= (Math/sqrt 32/7) (standard-deviation [2 4 4 4 5 5 7 9])) "wikipedia example")
    (is (thrown? ArithmeticException (standard-deviation [123])) "divide by zero")
    (is (= 0.0 (standard-deviation [1 1])))))

(run-tests)