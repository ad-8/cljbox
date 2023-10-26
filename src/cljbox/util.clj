(ns cljbox.util
  (:require
   [clojure.data.csv :as csv]
   [clj-http.client :as client]
   [clojure.java.io :as io]))

(defn read-csv-file
  "Reads the file at path and returns a lazy seq of vectors, each vector representing 
   a row in the csv file. Includes the header row."
  [path]
  (with-open [reader (io/reader path)]
    (doall
     (csv/read-csv reader))))

(defn write-csv-file
  "Writes content to the file located at path. Content should be a seq 
   of vectors, which represent the rows in the csv file."
  [path content]
  (with-open [writer (io/writer path)]
    (csv/write-csv writer content)))

(defn get-response-body
  "Makes a GET request to url and returns the response body."
  [url]
  (let [resp (client/get url {:cookie-policy :none})]
    (:body resp)))
