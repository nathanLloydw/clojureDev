(ns notepad)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

;;introduction to clojure ( learning curve )

(defn add
  ([a b] (+ a b))
  ([a b c] (+ a b c)))

(defn fact [n]
  (if (zero? n)
    1
    (* n (fact (dec n)))))

(defn inc-1st [lis]
  ( cons( +(first lis) 1) (rest lis)))

(defn vec-1st [lis]
  ( cons ( vec( list( first lis))) ( rest lis)))

(defn length [lis]
  (if (empty? lis)
    0
    (inc (length (rest lis)))))

(defn sum-list [lis]
  (if (empty? lis)
    0
    (+ (first lis) (sum-list (rest lis)))))

(defn spam-lis [lis]
  (if (empty? lis)
    0
    (cons 'spam (spam-lis (rest lis)))))

(defn spam-num [lis]
  (cond
    (empty? lis)
    nil
    (number? (first lis))
    (cons 'spam (spam-num (rest lis)))

    :else
    (cons (first lis) (spam-num (rest lis)))))

(defn recursive-printer
  ([]
   (recursive-printer 0))
  ([iteration]
   (println iteration)
   (if (> iteration 3)
     (println "Goodbye!")
     (recursive-printer (inc iteration)))))

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))

(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))

;;(stats [3 4 10])

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

;;head recur
(defn cnt-dwn-head
  ([goal free]  (cnt-dwn-head goal free nil))
  ([goal free used]
   (cond (zero? goal) used
         (neg?  goal) nil
         (empty? free) nil
         :else
         (or (cnt-dwn-head (- goal (first free)) (rest free) (cons (first free) used))
             (cnt-dwn-head goal (rest free) used)))))

;;tail recur
(defn cnt-dwn-tail
  ([goal free]  (cnt-dwn-tail goal free nil))
  ([goal free used]
   (cond (zero? goal) used
         (neg?  goal) nil
         (empty? free) nil
         :else
         (or (cnt-dwn-tail (- goal (first free)) (rest free) (cons (first free) used))
             (cnt-dwn-tail goal (rest free) used)))))

