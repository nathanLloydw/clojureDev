(ns projects.functions)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

;; no paramaters example
(defn greet
  "says hello"
  []
  "hello")

;;single paramater example
(defn greeting
  "says hello to user"
  [name]
  (str "hellooo " name ", fancy seeing you here"))

;;multiple paramater and remaining example, not sure why the & c has a weird print format though.
;;multiple-arity function requires extra wrapping and seen below:
(defn groupGreeting
  "function documentation..."
  ([a]
   (str "hellooo " a ", fancy seeing you here again"))
  ([a b]
    (str "hellooo " a ", fancy seeing you here again. Oh and you brought " b))
  ([a b & c]
    (str "hellooo " a ", fancy seeing you here again. Oh and you brought " b ". Who are these though: " (clojure.string/join ", " c) "?" )))

;;default override example, if not enough args are given it can create its own.
(defn attack
  "describes an attack with a default or given weapon"
  ([name weapon]
  (str name " attacks the giant goblin with his " weapon))
  ([name]
   (attack name "stick")))

;;if you map the output and pass a list/vector the returning map will contain the function called x times over.
;;e.g. (map attack '("nathan" "sarah" "billy" "sam")) || (map attack ["nathan" "sarah" "billy" "sam"])
;; || (map attack ["nathan" "sarah" "billy" "sam" ] ["spoon" "fork" "spork" "silver sword"])

;;this is deconstructing in use, as you can see you can split where the value would be into sub values by putting it in brackets.
;;e.g. (deconstructingTest ["a" "b" "c"] ["d" "e" "f"] ["g" "h" "i"])
(defn deconstructingTest [[a b c] [d e f] [g h i]]
  (str c " " b " " a " " d " " e " " h " " g " " i " " f))