(ns cljbox.datetime)

(defn to-hms
  "Converts a duration in seconds into hours, minutes, and seconds."
  [s]
  (if (< s 0)
    (throw (IllegalArgumentException. "Input seconds must be non-negative"))
    (let [h (quot s 3600)
          m (quot (rem s 3600) 60)
          s (rem s 60)]
      [h m s])))
