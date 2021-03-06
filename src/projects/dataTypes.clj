(ns projects.dataTypes)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

(def this-is-a-number 1213456)
(def this-is-a-string "abcdefg")
;; you can turn anything into a string with the function str, or push them together with concat

(def this-is-a-keyword :keyword)
;;keywords are mainly used in maps or to map data output. i cant imagine them ever being used like this.

(def this-is-a-list '(a b c d "asfd" 1 3 5))
;;you cant retrive the elements with the get function, here you use first, last or nth.
;;you can create a list with the function list, you can also use conj to add to the list

(def this-is-a-vector ["this" "is" "a" "vector"])
;;with a vector you can use the get function to look up a element. e.g. (get this-is-a-vector 1)
;;vectors can contain any types
;;you can create vectors with the vector function and conj elements to it with conj.

(def this-is-a-hashmap {:firstname "nathan" :lastname "williams"})
(def this-is-a-nested-hashmap {:a "1" :b "2" :c 3 :d {:a "found me"}})
;; you can create a hash-map using the function hash-map, you can also you the function get to lookup a value. e.g. (get this-is-a-hashmap :firstname)
;; thought you can look up an item in a non nested map by treating it like a function.
;; get will return nil if it doesnt find your key or return a default value given
;; you can use the get-in function to look up nested value. e.g. (get-in this-is-a-nested-hashmap [:d :a])
;;hash-maps can contain any types

(def this-is-a-hashset #{"kurt vonnegut" 20 :icicle})
;;hashsets can be created with the hash-set or set function
;;you cant have duplicate values in a hash set.
;;you can create a hash set from vectors or lists by using the set function.
;;you can use the contains? function to see if it has the value, it returns true or false.
;;otherwise you can use get to retrive the value

;;this is a vector of maps -- getting a bit confusing now:

(def asym-hobbit-body-parts
  [{:name "head" :size 3}
   {:name "left-eye" :size 1}
   {:name "left-ear" :size 1}
   {:name "mouth" :size 1}
   {:name "nose" :size 1}
   {:name "neck" :size 2}
   {:name "left-shoulder" :size 3}
   {:name "left-upper-arm" :size 3}
   {:name "chest" :size 10}
   {:name "back" :size 10}
   {:name "left-forearm" :size 3}
   {:name "abdomen" :size 6}
   {:name "left-kidney" :size 1}
   {:name "left-hand" :size 2}
   {:name "left-knee" :size 2}
   {:name "left-thigh" :size 4}
   {:name "left-lower-leg" :size 3}
   {:name "left-achilles" :size 1}
   {:name "left-foot" :size 2}])

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])

;;the following are two sequences/vectors
(def human-consumption   [8.1 7.3 6.6 5.0])
(def critter-consumption [0.0 0.2 0.3 1.1])

(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your dad"}
   {:alias "Easter Bunny" :real "Your mum"}])

;;using the following function i can create map this data:
(defn unify-diet-data
  [human critter]
  {:human human
   :critter critter})
;;(map unify-diet-data human-consumption critter-consumption)

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true  :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true  :name "McMackson"}
   2 {:makes-blood-puns? true,  :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true,  :has-pulse? true  :name "Mickey Mouse"}
   4 {:makes-blood-puns? true,  :has-pulse? true  :name "Nathan Williams"}
   5 {:makes-blood-puns? false,  :has-pulse? true  :name "Count dooku"}
   6 {:makes-blood-puns? true,  :has-pulse? false  :name "Count drakula"}
   7 {:makes-blood-puns? true,  :has-pulse? true  :name "tony blair"}
   8 {:makes-blood-puns? true,  :has-pulse? true  :name "Sarah Mill"}
   9 {:makes-blood-puns? false,  :has-pulse? true  :name "Bob"}
   10{:makes-blood-puns? false,  :has-pulse? true  :name "Santa"} })