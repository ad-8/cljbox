(ns cljbox
  (:require
   [clojure.data.csv :as csv]
   [clojure.java.io :as io]))

(defn add
  "Adds two numbers and returns the result."
  [x y]
  (+ x y))

(defn read-csv-file
  "Reads the file at path and returns a lazy seq of vectors, each vector representing 
   a row in the csv file. Includes the header row."
  [path]
  (with-open [reader (io/reader path)]
    (doall
     (csv/read-csv reader))))

