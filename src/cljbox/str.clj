(ns cljbox.str
  (:require [clojure.string :as str]))

(defn split
  "clojure.string/split for use with the ->> (thread-last) macro."
  ([re s]       (str/split s re))
  ([re limit s] (str/split s re limit)))

(defn remove-ext
  "Removes the extension from a filename. Everything after the last dot in filename,
  including the dot itself, is considered the extension."
  [filename]
  (let [index-last-dot (str/last-index-of filename ".")]
    (if (nil? index-last-dot)
      filename
      (subs filename 0 index-last-dot))))

(defn safe-subs
  "Returns the substring of s beginning at start inclusive, and ending at end, exclusive.
   This function is safe to use without checking the length of the input string,
   as it will not throw an StringIndexOutOfBoundsException if the end index is out of bounds,
   but simply return the input string. For example (safe-subs \"foo\" 0 4) will return \"foo\"."
  [s start end]
  (if (>= (count s) end)
    (subs s start end)
    s))

;; https://andersmurphy.com/2019/01/15/clojure-string-interpolation.html
(defn replace-several [s & {:as replacements}]
  (reduce (fn [s [match replacement]]
            (clojure.string/replace s match replacement))
          s replacements))
