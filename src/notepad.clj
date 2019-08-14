(ns notepad)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])


(defn inc-1st [lis]
  ( cons( +(first lis) 1) (rest lis)))

(defn vec-1st [lis]
  ( cons ( vec( list( first lis))) ( rest lis)))

;;lmg woop
(defn lmg [x] (list (+ x 2) (* x 3) (- x 7)))

(defn canDo? [start goal lmg]
  (cond (= start goal) (do (println start) true)
        (> start goal) false
        (< start 0) false
        :else
        (do (println start)
            (or (canDo? (first (lmg start)) goal lmg)
                (canDo? (second (lmg start)) goal lmg)
                (canDo? (nth (lmg start) 2) goal lmg)))))

;;word finder
(def word '#{cat rat bat mat})

(defn word?
  ([words]  (word? words [] []))

  ([words nouns other]
   (cond (empty? words)
         {:nouns nouns, :rubbish other}

         (word (first words))
         (word? (rest words) (conj nouns (first words)) other)

         :else
         (word? (rest words) nouns (conj other (first words)))
         )))

(defn math [func]
  (func 1 2))