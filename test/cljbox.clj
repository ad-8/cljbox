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
    (is (= [1 1 1] (dt/seconds->hms 3661)))
    (is (= [0 1 0] (dt/seconds->hms 60)))
    (is (= [2 30 0] (dt/seconds->hms 9000)))
    (is (= [0 0 0] (dt/seconds->hms 0)))
    (is (= [24 0 0] (dt/seconds->hms 86400)))
    (is (= [0 0 1] (dt/seconds->hms 1)))
    (is (= [0 0 59] (dt/seconds->hms 59)))
    (is (= [0 1 0] (dt/seconds->hms 60)))
    (is (= [0 59 59] (dt/seconds->hms 3599)))
    (is (= [1 0 0] (dt/seconds->hms 3600)))
    (is (= [1 2 3] (dt/seconds->hms 3723)))
    (is (= [23 59 59] (dt/seconds->hms 86399)))
    (is (thrown? IllegalArgumentException (dt/seconds->hms -1)))))


(deftest test-my-subs
  (is (= (b.str/safe-subs "foo" 0 0) ""))
  (is (= (b.str/safe-subs "foo" 0 1) "f"))
  (is (= (b.str/safe-subs "foo" 0 2) "fo"))
  (is (= (b.str/safe-subs "foo" 0 3) "foo"))
  (is (= (b.str/safe-subs "foo" 0 4) "foo"))
  (is (= (b.str/safe-subs "foobar" 1 4) "oob"))
  (is (= (b.str/safe-subs "baz" 0 10) "baz"))          ; Length less than 'end'
  (is (= (b.str/safe-subs "qux" 0 3) "qux"))           ; Length equal to 'end'
  (is (= (b.str/safe-subs "quux" 0 2) "qu"))           ; Length greater than 'end'
  (is (= (b.str/safe-subs "hello" 1 5) "ello"))         ; Starting from index 1
  (is (= (b.str/safe-subs "world" 0 0) ""))            ; Empty substring
  (is (= (b.str/safe-subs "" 0 5) "")))                ; Empty string

(deftest test-timestamp->datetime
  (testing "todo"
    (is (= "1970-01-01T01:00+01:00[Europe/Berlin]" (str (dt/timestamp->datetime 0))))
    (is (= "2009-02-14T00:31:30+01:00[Europe/Berlin]" (str (dt/timestamp->datetime 1234567890))))))

(deftest test-datetime
  (testing "todo put all tests for functions from datetime.clj here"
    (is (= (dt/seconds->dhms 0) {:days 0, :hours 0, :minutes 0, :seconds 0}))
    (is (= (dt/seconds->dhms 1) {:days 0, :hours 0, :minutes 0, :seconds 1}))
    (is (= (dt/seconds->dhms 60) {:days 0, :hours 0, :minutes 1, :seconds 0}))
    (is (= (dt/seconds->dhms 3600) {:days 0, :hours 1, :minutes 0, :seconds 0}))
    (is (= (dt/seconds->dhms 86400) {:days 1, :hours 0, :minutes 0, :seconds 0}))
    (is (thrown? IllegalArgumentException (dt/seconds->dhms -1)))
    ; TODO handle negative input
    ;(is (= (dt/seconds->dhms -3665) {:days 0, :hours -1, :minutes -1, :seconds -5}))
    (is (= (dt/seconds->dhms 123456789) {:days 1428 :hours 21 :minutes 33 :seconds 9}))))

(run-tests)

