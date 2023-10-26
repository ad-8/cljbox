(ns cljbox-test
  (:require [clojure.test :refer :all]
            [cljbox :refer :all]))

(deftest addition
  (is (= 3 (add 1 2)))
  (is (= -3 (add -1 -2)))
  (is (= 0 (add 0 0)) "test adding zeros"))

(run-tests)
