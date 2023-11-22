(ns cljbox.datetime)

(defn seconds->hms
  "Converts a duration in seconds into hours, minutes, and seconds."
  [s]
  (if (< s 0)
    (throw (IllegalArgumentException. "Input seconds must be non-negative"))
    (let [h (quot s 3600)
          m (quot (rem s 3600) 60)
          s (rem s 60)]
      [h m s])))


(defn seconds->dhms
  "Converts a duration in seconds into a more human-readable format 
   with days, hours, minutes, and seconds."
  [s]
  (if (< s 0)
    (throw (IllegalArgumentException. "Input seconds must be non-negative"))
    (let [days (quot s 86400)
          remaining-seconds (mod s 86400)
          hours (quot remaining-seconds 3600)
          remaining-seconds (mod remaining-seconds 3600)
          minutes (quot remaining-seconds 60)
          seconds (mod remaining-seconds 60)]
      {:days days :hours hours :minutes minutes :seconds seconds})))


(defn timestamp->datetime
  "Converts a unix timestamp (in seconds) to a java.time.ZonedDateTime object
   with the local (system default) time zone."
  [ts]
  (let [instant (java.time.Instant/ofEpochSecond ts)
        zone-id (java.time.ZoneId/systemDefault)
        zoned-dt (.. instant (atZone zone-id))]
    zoned-dt))
