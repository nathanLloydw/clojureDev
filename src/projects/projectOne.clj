(ns projects.projectOne)


(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])


;;functions created to help my solutions
(defn theMath [lis]
  ((juxt filter remove) number? (flatten lis)))

(defn drop-nth [n coll]
  (keep-indexed #(if (not= %1 n) %2) coll))


;;(splitM1[1 2 5 3 "A" "H" 5 "C"]) | solution one
(defn splitM1 [lis]
  (time (sort(map str (flatten lis)))))

;;(splitM2[1 2 5 3 "A" "H" 5 "C"]) | solution two
(defn splitM2 [lis]
  (time(sort-by str (flatten lis))))

;;(splitM3[1 2 5 3 "A" "H" 5 "C"]) | solution three
(defn splitM3 [lis]
  (time ((juxt filter remove) number? (flatten lis))))

;;(splitM4[1 2 5 3 "A" "H" 5 "C"]) | solution four
(defn splitM4 [lis]
  (group-by  number? (flatten lis)))

;;(splitM5 '(1 2 3 4 5 6 "a" 4 "b")) | solution five

(defn splitM5 [lis]
  (time(println :numbers (first(theMath lis)) :letters/words (first (rest (theMath lis)) ))))

;;(splitM6 '(1 2 3 4 5 6 "a" 4 "b")) | solution six

(defn splitM6
  ([lis]
   (time(splitM6 (flatten lis) 0)))

  ([lis i]
   (if (< i (count lis))
     (do
       (println (nth lis i))
       (if (number? (nth lis i))
         (splitM6  (cons (nth lis i) (drop-nth i lis)) (+ i 1))
         (splitM6 lis (+ i 1))))
     lis)))

(defn splitM7
  ([lis]
   (splitM7 (flatten lis) [] []))
  ([lis numbers symbols]
   (cond (empty? lis)
         {:numbers numbers, :symbols symbols}

         (number? (first lis))
         (splitM7 (rest lis) (conj numbers (first lis)) symbols)

         :else
         (splitM7 (rest lis) numbers (conj symbols (first lis)) )
         ))
  )

;;useful functions:
;;concat
;;interleave
;;interpose - ands a keyword between each variable in a vector
;;apply str - messing with strings turn them into characters, this turns it back
;;seq - turns string into characters
;;shuffle
;;split-at - splits when to serperate vectors when conditions are met
;;split-with - uses a function to decide when
;;filter - can filter a sequince with a function such as pos? neg? string?
;;remove string?
;;partition,partition-all, partition-by
;;frequencies - removes duplucutes with number
;;group-by :keyword
;;reduce - puts elements into one list or map or something but removes duplicates
;;reductions - does same as above but shows how it changes
;;into - reducing elements into a map, list, etc...
;; when - does the folling if true
;; when-not does the following if false
;; their is also an if-not which flips the follwing conditional answers
;; when-let is when and let mixed
;; while repeats till an expression is false
;; cond can do many conditional answers is one go
;; condp
;; case
;; (rand)
;; (repeat 3 "hi")
;; (take 10 (iterate inc 0) )
;; (range 5 100 5)
;; (take 10 (cycle [ 1 2 3]))
;; max