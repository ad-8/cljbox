(ns cljbox.util
  (:require
   [clojure.data.csv :as csv]
   [clj-http.client :as client]
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

(defn get-response-body
  "Makes a GET request to url and returns the response body."
  [url]
  (let [resp (client/get url {:cookie-policy :none})]
    (:body resp)))
