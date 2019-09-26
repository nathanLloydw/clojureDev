(ns projects.recap)

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
