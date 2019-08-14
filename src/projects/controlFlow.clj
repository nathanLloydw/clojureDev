(ns projects.controlFlow)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

;;---------------------------------------------------- Conditional -----------------------------------------------------
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

;;clojure operations follow this syntax: opening parenthesis, operator, operands, closing parenthesis.
;; A function call is just an operation, the first item in an operation has to be a function though.
;;this means you can use functions as the operator.
;; e.g. ( (or + -) 1 2 3 4) && ( (or false -) 1 2 3 4) && ((first [+ 0]) 1 2 3)

(defn errorMessage
  [severity]
  (str "OH GOD! IT'S A DISASTER! WE'RE "
       (cond
         (= severity :fine) "GOING HOME EARLY!"
         (= severity :mild) "MILDLY INCONVENIENCED!"
         (= severity :serious) "DOOOOOOOMED!"
         :else "CONFUUSSSEEEDDDD!")))
;;here i have used the cond function to deal with multiple conditions.

;;another cond example
(defn rps [p1 p2]
  (cond
    (= p1 p2) "Draw!"
    (and (= p1 "rock") (= p2 "paper")) "Player 2 won!"
    (and (= p1 "paper") (= p2 "rock")) "Player 1 won!"
    (and (= p1 "scissors") (= p2 "paper")) "Player 1 won!"
    (and (= p1 "paper") (= p2 "scissors")) "Player 2 won!"
    (and (= p1 "rock") (= p2 "scissors")) "Player 1 won!"
    (and (= p1 "scissors") (= p2 "rock")) "Player 2 won!"))

;;------------------------------------------------------ Recursion -----------------------------------------------------

;;you can recur in a function by simply calling it again and pass different paramaters, for example:

(defn fact-head ([x] (fact-head x (- x 1)))      ;;this is known as tail recursion (trace-vars fact-head)
  ([x c]
   (if (= c 1)
     x
     (fact-head (* x c) (dec c)))))

(defn fact-tail [x]                              ;;this is known as head recursion (trace-vars fact-tail)
  (if (= x 1)
    1
    (* x (fact-tail (dec x)))))

;; here is another couple of example of recursion through self call:
(defn length [lis]
  (if (empty? lis)
    0
    (inc (length (rest lis)))))

(defn sum-list [lis]
  (if (empty? lis)
    0
    (+ (first lis) (sum-list (rest lis)))))

(defn spam-num [lis]
  (cond
    (empty? lis)
    nil
    (number? (first lis))
    (cons 'spam (spam-num (rest lis)))

    :else
    (cons (first lis) (spam-num (rest lis)))))

;;this next couple of example uses the built in loop/recur function:
(defn factorial [x]
  (loop [fact x count (- x 1)]
    (if (= count 1)
      fact
      (recur (* fact count) (dec count)))))

;;this example does not have the loop in because i dont need any more variables:
(defn boom [x]
    (if (= x 0)
      (println "Boooom!!")
      (do (println x) (recur (- x 1)))))


;;using the loop/recur function only works from the tail position.

;; loop is the recursion point for recur. The symbols in loop's binding-forms are bound to their respective init-exprs
;; and rebound to the values of recur's exprs before the next execution of the loop's body.

