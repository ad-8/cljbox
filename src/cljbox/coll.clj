(ns cljbox.coll)

(defn coll-contains?
  "DEPRECATED. Use `cljbox.coll/seq-contains?`.
   Returns true if val is present in the given collection, otherwise returns false.
   Uses `some` under the hood, so works as expected for lists and vectors, unlike `contains?`."
  [coll val]
  (if (some #(= val %) coll)
    true
    false))


(defn seq-contains?
  "Returns true if val is present in the given collection, otherwise returns false.
   Uses `some` under the hood, so works as expected for lists and vectors, unlike `contains?`."
  [coll val]
  (if (some #(= val %) coll)
    true
    false))

; https://stackoverflow.com/a/45951160
(defn map-next-el 
  "Takes a coll of items and returns a sorted map, where each k-v pair is a mapping
   of an item n in the coll to the item n+1 that follows it. 
   For the key representing the last item in coll, its value is the first item."
  [coll]
  (->> (cycle coll)
       (take (inc (count coll)))
       (partition 2 1)
       (map vec) ; doesn't work without - why is a good question
       (into (sorted-map))))
