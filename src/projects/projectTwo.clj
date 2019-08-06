(ns projects.projectTwo)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[projects.dataTypes :refer :all])

;;-------------------------------------------- Symmetry & hobits -------------------------------------------------------

;;this example looks like it deconstructs it whilst running the body/return
(defn matching-part00
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

;;this is an example online, after looking into the different functions in use i finally understand it
(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining (into final-body-parts (set [part (matching-part00 part)])))))))

;; remove nil values:
;; (remove nil? (map matching-part asym-hobbit-body-parts))
;; turn the list into a vector:
;; (into [] (remove nil? (map matching-part02 asym-hobbit-body-parts)))
;; i can put it all into one map using:
;; (conj asym-hobbit-body-parts (remove nil? (map matching-part asym-hobbit-body-parts)))
;; to bring them both together with the right data type i can use:
;; (into (into [] (remove nil? (map matching-part02 asym-hobbit-body-parts))) asym-hobbit-body-parts)

;;this version only returns the new parts and with this calll it doesnt return any nil values:
;;using the function i mentioned above i wrote a version which i understand,
;;the only difference is the order the maps are listed

(defn matching-part01
  [{part :name size :size}]
  (if (clojure.string/includes? part "left")
    (hash-map :name (clojure.string/replace part #"^left-" "right-") :size size)))

(defn symmetrize-hobit
  [hobit]
  (into (into [] (remove nil? (map matching-part01 hobit))) hobit))

;; (symmetrize-hobit asym-hobbit-body-parts)
;; (into [] (set [(matching-part01 (nth asym-hobbit-body-parts 1)) (nth asym-hobbit-body-parts 1)]))

;; the reduce function can apply a function to every arguement in a list or vector
;; for example: (reduce + 10 [1 2 3 4])
;; using this function i can write i much better symetrizer:

;;(symmetrize-hobit-final asym-hobbit-body-parts)
(defn symmetrize-hobit-final
  "Expects a seq of maps that have a :name and :size"
  [hobit]
  (reduce
    (fn [body part] (remove nil? (into body (set [part (matching-part01 part)]))))
    [] hobit))
;; in this example the reuduce function applies the annoymous function to each item in the vector

;;-------------------------------------------- hit the hobit -----------------------------------------------------------

;; in this following function i use many functions i have learnt previously which follow:
;; let, loop, if, map and recur. the only function which is new is rand. which is self explanatory.

;;(hit (symmetrize-hobit-final asym-hobbit-body-parts))
(defn hit
  [asym-body-parts]
  (let [sym-parts (symmetrize-hobit-final asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
            accumulated-size (:size part)]
       (if (> accumulated-size target)
         (str "you hit the hobit in the " (:name part) " for " (rand-int (:size part)) " points")
         (recur remaining (+ accumulated-size (:size (first remaining))))))))