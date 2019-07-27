(ns projects.controlFlow)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

(defn ifOne [a b]
  (if (> a b)
    "true/return values"
    "false/return values"))

(defn ifTwo [a b]
  (if (> a b)
    "true"))

(defn ifThree [a b]
  (if (> a b)
    (do (println "true")
        (println "true")
        (println "true")
        (println "can do as much as id like...")
        "true/return value")
    (do (println "false")
        (println "false")
        "false/return value")))

;;use if you want to do multiple things when a condition is true
(defn whenOne [a b]
  (when (> a b)
    (println "true")
    (println "do and if in one line")
    (println "still true")
    "true/return value"))