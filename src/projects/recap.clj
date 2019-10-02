(ns projects.recap)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[imports.ops_search :refer :all])

(defn nthItem [a b]
  (nth b a))

(defn sum [a]
  (reduce + a ))

(defn odd
([a]
  (odd a [] []))
([a odds even]
  (cond
    (empty? a) (hash-map :odd odds :even even)
    (odd? (first a)) (odd (rest a) (conj odds (first a)) even)
    :else (odd (rest a) odds (conj even (first a))))))

(defn lmg [x] (list (+ x 15) (* x 6) (- x 12)))

(defn canDo?
  ([start goal lmg]
    (canDo? start goal lmg (conj [] start)))
  ([start goal lmg been]
   (cond (= start goal) (do (println start) (println "SUCCESS!") been)
         (> start goal) false
         (< start 0) false
         (not (= (count been) (count (into [] (set been))))) false
         :else
         (do (println start)
             (or (canDo? (first (lmg start)) goal lmg (conj been (first (lmg start))))
                 (canDo? (second (lmg start)) goal lmg (conj been (second (lmg start))))
                 (canDo? (nth (lmg start) 2) goal lmg (conj been (nth (lmg start) 2))))))))

