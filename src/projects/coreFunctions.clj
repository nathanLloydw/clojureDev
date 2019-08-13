(ns projects.coreFunctions)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[projects.dataTypes :refer :all])

;;---------------------------------------------- Reduce function -------------------------------------------------------

;;the reduce function is a way to iterate through a list or vector from start to end
;; and apply the same function to those values, it is a good function to use for recursion

(defn sum-of [vec]
  (reduce + 0 vec))

(defn highest-num [vec]
  (reduce
    (fn [a b]
      (if (> a b) a b))
    0       ;; this is the starting value which the larger number gets passed to until the vector is empty.
    vec))   ;; we move through the list comparing the value passed and the vector[pos] until we have the largest

;;reduce applies the given function to each item in a list, array or map, simple.

;;-------------------------------------------- Assoc function ----------------------------------------------------------

;;(reduce plus-one {} {:max 12 :min 10})
(defn plus-one [new-map [key val]] (assoc new-map key (inc val)))

(defn filter-less-than [new-map [key val]] (if (> val 4) (assoc new-map key val) new-map ))
;;(reduce filter-less-than {} {:max 2 :min 10}) and (reduce filter-less-than {} {:max 12 :min 10})

;;the main feature in these functions is the assoc function,
;;you can use it to create a map with given values or override the given keys with new values.
;; (assoc {} :a 1 :b 2) or (assoc nil :a 1 :b 2) and (assoc {:a 0 :b 0} :a 1 :b 2)

;; you can do the same with vectors but you need to pass the position aswell as follows
;; (assoc [] 0 5 1 5) and (assoc [0 1 2 3] 0 5 1 5)
;;           i   i                         i   i             (i = index)
;; you can put a list in at a specific index:
;; (assoc [0 1 2 3] 0 5 1 5 2 '(1 2 3 4)) and (assoc [] 0 5 1 5 2 '(1 2 3 4))
;;if you try to replace an index that does not exist it will crash.

;;------------------------------------- take, drop, take-while, and drop-while -----------------------------------------


;; the take function will take the first x values from a list or vector and put them into a list,
;; it will return all the items it has if it contains less than x even it is empty.

;; (take 3 []) and (take 3 [1 2]) and (take 3 [1 2 3 4 3 2 1 1 1]) and (take -3 [1 2 3 4 3 2 1 1 1])

;; the drop function does nearly the exact same but removes the first x values instead of taking them,
;; leaving you with what ever is left.

;; (drop 2 []) and (drop 2 [1]) and (drop 2 [1 2 3 4]) and (drop -2 [1 2 3 4 5])


;; using take and drop with the while keyword lets you loop through each item until a condition is met,
;; then take or drop what has been iterated. for example you can check for a negative value:

;; (drop-while neg? [-1 -2 3 4 5]) and (take-while neg? [-1 -2 3 4 5])

;; a good example of take and drop while in use with a anonymous function:
;; (take-while #(< (:month %) 3) food-journal) and (drop-while #(< (:month %) 3) food-journal)

;;----------------------------------------------------- FACT -----------------------------------------------------------
;; the take/drop-while function keep taking/dropping items till a condition is met, it does not check the whole seq.  ;;
;;----------------------------------------------------------------------------------------------------------------------

