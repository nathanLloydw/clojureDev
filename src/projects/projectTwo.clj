(ns projects.projectTwo)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[projects.dataTypes :refer :all])

;;this example looks like it deconstructs it whilst running the body/return
(defn matching-part00
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

;;this is another exmaple which i dont quite understand but i re-wrote it at the bottom
(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part00 part)])))))))

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