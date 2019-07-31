(ns projects.controlFlow)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

(defn ifOne [a b]
  (if (> a b)
    "true/return values"
    "false/return values"))

(defn ifTwo [a b]
  (if (> a b)
    "true"))

(defn ifThree [a b]
  (if (> a b)
    (do (println "true")
        (println "true")
        (println "true")
        (println "can do as much as id like...")
        "true/return value")
    (do (println "false")
        (println "false")
        "false/return value")))

;;use if you want to do multiple things when a condition is true
(defn whenOne [a b]
  (when (> a b)
    (println "true")
    (println "do and if in one line")
    (println "still true")
    "true/return value"))

;;clojure operations follow this syntax: opening parenthesis, operator, operands, closing parenthesis. A function call is just an operation,
;;the first item in an operation has to be a function though.
;;this means you can use functions as the operator. e.g. ( (or + -) 1 2 3 4) && ( (or false -) 1 2 3 4) && ((first [+ 0]) 1 2 3)

(defn errorMessage
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (cond
         (= severity :fine) "GOING HOME EARLY!"
         (= severity :mild) "MILDLY INCONVENIENCED!"
         (= severity :serious) "DOOOOOOOMED!"
         :else "CONFUUSSSEEEDDDD!")))
;;here i have used the cond function to deal with multiple conditions.