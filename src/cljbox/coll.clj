(ns cljbox.coll)

(defn coll-contains?
  "Returns true if val is present in the given collection, otherwise returns false.
   Uses `some` under the hood, so works as expected for lists and vectors, unlike `contains?`."
  [coll val]
  (if (some #(= val %) coll)
    true
    false))
