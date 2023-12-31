(ns cljbox
  (:require [clojure.test :refer :all]
            [cljbox.math :refer :all]
            [cljbox.str :as b.str]
            [cljbox.coll :as coll]
            [cljbox.datetime :as dt]))

(deftest math-test
  (testing "round-to"
    (let [PI 3.1415926535]
      (is (= 3.14 (round-to 2 PI)))
      (is (= 3.1 (round-to 1 PI)))
      (is (= 3.0 (round-to 0 PI)))
      (is (= 4.0 (round-to 0 3.5)))))
  (testing "mean"
    (is (= nil (mean [])))
    (is (= 1 (mean [1])))
    (is (= 1/2 (mean [0 1])))
    (is (= 1 (mean [0 1 2]))))
  (testing "median"
    (is (= nil (median [])))
    (is (= 1 (median [1])))
    (is (= 1 (median [0 1 2])))
    (is (= 3/2 (median [0 1 2 3]))))
  (testing "standard-deviation"
    (is (= (Math/sqrt 32/7) (standard-deviation [2 4 4 4 5 5 7 9])) "wikipedia example")
    (is (= nil (standard-deviation [])))
    (is (= nil (standard-deviation [1])))
    (is (= 1.0 (standard-deviation [1 2 3])))
    (is (= 0.0 (standard-deviation [1 1])))))

(deftest str-test
  (testing "split"
    (is (= ["foo" "bar" "baz"] (->> "foo/bar/baz" (b.str/split #"/"))))
    (is (= ["foo" "bar/baz"] (->> "foo/bar/baz" (b.str/split #"/" 2)))))
  (testing "remove-ext"
    (is (= "foo" (b.str/remove-ext "foo.txt")))
    (is (= "foo.txt" (b.str/remove-ext "foo.txt.bak")) "just how remove-ext works")
    (is (= "bar" (b.str/remove-ext "bar")))
    (is (= "" (b.str/remove-ext "")))))

(deftest coll-test
  (testing "seq-contains?"
    (is (= false (coll/coll-contains? [1 2 3] 0)))
    (is (= true (coll/coll-contains? [1 2 3] 1)))
    (is (= false (coll/coll-contains? '(:a \b "c") \x)))
    (is (= true (coll/coll-contains? '(:a \b "c") \b)))
    (is (= false (coll/coll-contains? [] 1337)))
    (is (= true (coll/coll-contains? (seq "clojure") \j)))))

(deftest test-to-hms
  (testing "converting seconds to hours, minutes, and seconds"
    (is (= [1 1 1] (dt/to-hms 3661)))
    (is (= [0 1 0] (dt/to-hms 60)))
    (is (= [2 30 0] (dt/to-hms 9000)))
    (is (= [0 0 0] (dt/to-hms 0)))
    (is (= [24 0 0] (dt/to-hms 86400)))
    (is (= [0 0 1] (dt/to-hms 1)))
    (is (= [0 0 59] (dt/to-hms 59)))
    (is (= [0 1 0] (dt/to-hms 60)))
    (is (= [0 59 59] (dt/to-hms 3599)))
    (is (= [1 0 0] (dt/to-hms 3600)))
    (is (= [1 2 3] (dt/to-hms 3723)))
    (is (= [23 59 59] (dt/to-hms 86399)))
    (is (thrown? IllegalArgumentException (dt/to-hms -1)))))

(run-tests)
