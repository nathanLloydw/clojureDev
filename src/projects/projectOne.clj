(ns projects.projectOne)


(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

;;introduction to clojure ( learning curve )

(defn inc-1st [lis]
  ( cons( +(first lis) 1) (rest lis)))

(defn vec-1st [lis]
  ( cons ( vec( list( first lis))) ( rest lis)))

(defn grammer
  [name & things]
  (str "Hi " name ", here is a list: "
       (clojure.string/join ", " things)))

(def anom-multiplier (fn [x] (* x 3)))

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

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

;;(map :real identities)

(def food-journal
  [{:month 1 :day 1 :junk 5.3 :veg 2.3}
   {:month 1 :day 2 :junk 5.1 :veg 2.0}
   {:month 2 :day 1 :junk 4.9 :veg 2.1}
   {:month 2 :day 2 :junk 5.0 :veg 2.5}
   {:month 3 :day 1 :junk 4.2 :veg 3.3}
   {:month 3 :day 2 :junk 4.0 :veg 3.8}
   {:month 4 :day 1 :junk 3.7 :veg 3.9}
   {:month 4 :day 2 :junk 3.7 :veg 3.6}])

;;(take-while #(< (:month %) 3) food-journal)

;;(drop-while #(< (:month %) 3) food-journal)

;;(take-while #(< (:month %) 4) (drop-while #(< (:month %) 2) food-journal))

;;(filter #(< (:junk %) 5) food-journal)

;;(filter #(< (:month %) 3) food-journal)

;;(some #(> (:veg %) 3) food-journal)


;;unrealated but :
;;(concat (take 8 (repeat "na")) ["Batman!"])

;;(take 3 (repeatedly (fn [] (rand-int 10))))