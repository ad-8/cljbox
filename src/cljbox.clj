(ns cljbox
  (:require
   [clojure.data.csv :as csv]
   [clj-http.client :as client]
   [clojure.java.io :as io]
   [clojure.string :as str]))

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

(defn str-split
  "str/split for usage with the ->> (thread-last) macro."
  [re s]
  (str/split s re))

(defn get-response-body
  "Makes a GET request to url and returns the response body."
  [url]
  (let [resp (client/get url {:cookie-policy :none})]
    (:body resp)))
