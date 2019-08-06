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

;;------------------------------------------------- random function ----------------------------------------------------
;;you can also use the function identity to return the argument with out altering it.
;;----------------------------------------------------------------------------------------------------------------------

;; ------------------------------------------------ Deconstructing -----------------------------------------------------

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
;; In the following example, the original map is still accessable with in treasure-location:

(defn receive-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  (println (str "all together now: " treasure-location)))
;;In general, you can think of destructuring as instructing Clojure on how to associate
;; names with values in a list, map, set, or vector.

;;-------------------------------------------- Let & In body deconstruction --------------------------------------------

;;basic example of let being used to mess with the data we was given.
;; this can be done without let but this makes it more simple
(defn grammer-me-up [she he]
  (let [hers (str she "'s")
        his (str he "'s")]
    (str hers " and " his)))

;;we can bind an anonymous with in the function for local use.
;;here we can see we use let on three values, we first bind a function and then bind strings to that functions output,
;; for example:
(defn grammer-me-easy [she he]
  (let [possessive (fn [x] (str x "'s"))
        hers (possessive she)
        his (possessive he)]
    (str his " and " hers)))

;;another example testing if three numbers make a right angle:
(defn right-triangle? [coll]
  (let [a-square (* (nth coll 0) (nth coll 0))
        b-square (* (nth coll 1) (nth coll 1))
        c-square (* (nth coll 2) (nth coll 2))]
    (do (println a-square b-square c-square) (= (+ a-square b-square) c-square))))

;;to make this function a little bit more readable and better i can deconstruct the params:
(defn right-triangle? [[a b c]]
  (let [a-square (* a a)
        b-square (* b b)
        c-square (* c c)]
    (do (println a-square b-square c-square) (= (+ a-square b-square) c-square))))

;;let is simply deconstructing outside of the params container.

;;-------------------------------------------- Anonymous Functions & returns -------------------------------------------

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

;;---------------------------------------------- Reduce function -------------------------------------------------------

;;the reduce function is a way to iterate through a list or vector from start to end
;; and apply the same function to those values, it is a good function to use for recursion

(defn sum-of [vec]
  (reduce + 0 vec))

(defn highest-num [vec]
  (reduce
    (fn [a b]
      (if (> a b) a b))
          0       ;; this is the starting value which the larger number gets passed to until the vector is empty.
          vec))   ;; we move through the list comparing the value passed and the vector[pos] until we have the largest
