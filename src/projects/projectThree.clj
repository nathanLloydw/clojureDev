(ns projects.projectThree)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])


(defn lazy-contains? [col key]
  (some #{key} col))

(defn list-contains? [coll value]
  (let [s (seq coll)]
    (if s
      (if (= (first s) value) true (recur (rest s) value))
      false)))

(defn includes?
  "True if s includes substr."
  {:added "1.8"}
  [^CharSequence s ^CharSequence substr]
  (.contains (.toString s) substr))

(defn index-of [e coll] (first (keep-indexed #(if (= e %2) %1) coll)))

(def rooms
  '((connects front-door front-garden hall)
     (connects front-door hall front-garden)
     (connects yellow-door hall bedroom)
     (connects yellow-door bedroom hall)
     (connects blue-door hall kitchen)
    (connects blue-door kitchen hall)
    (connects green-door kitchen back-garden)
    (connects green-door back-garden kitchen)
     ))

;; final version : (get-to rooms 'front-garden 'back-garden)
(defn get-to
  ([rooms from to] (get-to rooms from to #{} []))
  ([rooms from to been-here route]
   (cond (= from to)
         route

         (been-here from)
         nil

         :else
         (mfor [['connects '?door from '?next] rooms]
               (get-to rooms (? next) to (conj been-here from) (conj route from))))))




;;(getTo rooms 'front-garden 'back-garden)
(defn getTo
  [rooms from to]
  (for [room rooms]
    (cond (= from to)
          (println "im their")
          (= (nth room 2) from)
          (getTo rooms (nth room 3) to))))

(defn getToM2
  [rooms from to]
  (cond (= from to)
        true

        (lazy-contains? (first rooms) from)
        (getToM2 (next rooms)(nth (first rooms) 3) to)

        :else
        false))

(defn getToM3
  ([rooms from to]
   (getToM3 rooms from to (str "been: " from " ")))
  ([rooms from to been]
   (cond (= from to)
         (str been)

         (nil? (next rooms))
         (str been ",ran out of rooms to move")

         (includes? (nth (first rooms) 2) (str from))
         (getToM3 (next rooms)(nth (first rooms) 3) to (str been (nth (first rooms)3) " "))

         :else
         (getToM3 (next rooms) from to (str been )))))

(defn getToM4
  ([rooms from to]
   (getToM4 rooms from to (str "been: " from " ")))
  ([rooms from to been]
   (cond (= from to)
         (str been)

         (nil? (next rooms))
         (str been ",ran out of rooms to move")

         (includes? (nth (first rooms) 2) (str from))
         (getToM4 (shuffle(next rooms))(nth (first rooms) 3) to (str been (nth (first rooms)3) " "))

         )))

(defn getToM5
  ([rooms location destination]
   (getToM5 rooms location destination  (str "route: " location " ") (str "dead ends: ")))
  ([rooms location destination route deadend]
   (cond (= location destination)
         (str route)

         (nil? (next rooms))
         (str route ",ran out of rooms to move")

         (not= location destination)
         (str "not their yet")

         :else
         (getToM5 rooms location destination (str route ) (str deadend)))))

