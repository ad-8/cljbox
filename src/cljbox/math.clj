(ns cljbox.math)

(defn add
  "Adds two numbers and returns the result."
  [x y]
  (+ x y))

(defn round-to
  "Round a double to the given precision (number of significant digits)"
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))
