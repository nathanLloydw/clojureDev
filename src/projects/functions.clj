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

;;this mapping feature can be used in functions e.g: (old-folk "billy" "sam" "terry")
;;do not understand why i cant put this into one function...
(defn old-folk [young-folk] (str "Get of my lawn " young-folk))
(defn young-folk [& youngFolks] (map old-folk youngFolks))

;;conditional function example - errorMessage
(defn errorMessage
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (cond
         (= severity :fine) "GOING HOME EARLY!"
         (= severity :mild) "MILDLY INCONVENIENCED!"
         (= severity :serious) "DOOOOOOOMED!"
          :else "CONFUUSSSEEEDDDD!")))
;;i have used the cond function for multiple conditions rather than the if for one condition.

;;----------------------------------- random function ---------------------------------
;;you can also use the function identity to return the argument with out altering it.
;;-------------------------------------------------------------------------------------

;; ---------------------------------- Deconstructing ----------------------------------

;;this is deconstructing in use, as you can see you can split where the value would be into sub values by putting it in brackets.
;;e.g. (deconstructingTest ["a" "b" "c"] ["d" "e" "f"] ["g" "h" "i"])
(defn deconstructingTest [[a b c] [d e f] [g h i]]
  (str c " " b " " a " " d " " e " " h " " g " " i " " f))

;;another example of deconstructing
;;the vector or list is split into the first two items and the remaining/list
(defn priorties
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))

;;deconstructing a map is a bit different, you dont put it into a vector but a map:
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

;;there’s a shorter syntax for that. This has the same result as the previous example:
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat : " lat))
  (println (str "Treasure lng : " lng)))

;;You can retain access to the original map argument by using the :as keyword.
;; In the following example, the original map is accessed with treasure-location:

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
;;In general, you can think of destructuring as instructing Clojure on how to associate
;; names with values in a list, map, set, or vector.

;;------------------------- Anonymous Functions & returns -------------------------------

;; anonymous function syntax,
;; cant be called to be used with in other functions all calls:
(fn [param-list] (str "body"))

;;example of anonymous function in use:
(def square-me (fn [x] (* x x)))

;;another more compact version of the anonymous function:
(def cube-me #(* % % %))

;;you can map the function to do many at the same time:
;;(map cube-me [1 2 3 4 5])
;;if your anonymous function takes multiple arguements use: %1 %2 %3 %&

;;in both the previous examples their are no sign of the variable x being sent,
;;thats because its not a function, its a variable. once called the function is returned.
;;you do not send the function a value the function is returned to it.



