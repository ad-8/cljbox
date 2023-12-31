(ns cljbox.math)

(defn round-to
  "Rounds a float or double to the given precision (number of significant digits)."
  [precision d]
  (let [factor (Math/pow 10 precision)]
    (/ (Math/round (* d factor)) factor)))

(defn mean
  "Returns the mean (average) of the numbers in coll."
  [coll]
  (let [sum (apply + coll)
        count (count coll)]
    (if (pos? count)
      (/ sum count)
      nil)))

(defn median
  "Returns the median of the numbers in coll."
  [coll]
  (if (= 0 (count coll))
    nil
    (let [sorted (sort coll)
          cnt (count sorted)
          halfway (quot cnt 2)]
      (if (odd? cnt)
        (nth sorted halfway)
        (let [bottom (dec halfway)
              bottom-val (nth sorted bottom)
              top-val (nth sorted halfway)]
          (mean [bottom-val top-val]))))))

(defn standard-deviation
  "Returns the standard deviation of the numbers in coll."
  [coll]
  (if (< (count coll) 2)
    nil
    (let [avg (mean coll)
          squares (for [x coll]
                    (let [x-avg (- x avg)]
                      (* x-avg x-avg)))
          total (count coll)]
      (-> (/ (apply + squares)
             (- total 1))
          (Math/sqrt)))))
