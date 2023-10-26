(ns cljbox.str
  (:require [clojure.string :as str]))

(defn split
  "str/split for use with the ->> (thread-last) macro."
  [re s]
  (str/split s re))
