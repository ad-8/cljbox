(ns cljbox.str
  (:require [clojure.string :as str]))

(defn split
  "str/split for use with the ->> (thread-last) macro."
  [re s]
  (str/split s re))

(defn remove-ext
  "Removes the extension from a filename. Everything after the last dot in filename,
  including the dot itself, is considered the extension."
  [filename]
  (let [index-last-dot (str/last-index-of filename ".")]
    (if (nil? index-last-dot)
      filename
      (subs filename 0 index-last-dot))))
