(ns projects.coreFunctions)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])
(use '[projects.dataTypes :refer :all])

;;---------------------------------------------- Reduce function -------------------------------------------------------

;; the reduce function is a way to iterate through a list or vector from start to end
;; and apply the same function to those values, it is a good function to use for recursion

(defn sum-of [vec]
  (reduce + 0 vec))

(defn highest-num [vec]
  (reduce
    (fn [a b]
      (if (> a b) a b))
    0       ;; this is the starting value which the larger number gets passed to until the vector is empty.
    vec))   ;; we move through the list comparing the value passed and the vector[pos] until we have the largest

;; reduce applies the given function to each item in a list, array or map, simple.

;;-------------------------------------------- Assoc function ----------------------------------------------------------

;; (reduce plus-one {} {:max 12 :min 10})
(defn plus-one [new-map [key val]] (assoc new-map key (inc val)))

(defn filter-less-than [new-map [key val]] (if (> val 4) (assoc new-map key val) new-map ))
;;(reduce filter-less-than {} {:max 2 :min 10}) and (reduce filter-less-than {} {:max 12 :min 10})

;; the main feature in these functions is the assoc function,
;; you can use it to create a map with given values or override the given keys with new values.
;; (assoc {} :a 1 :b 2) or (assoc nil :a 1 :b 2) and (assoc {:a 0 :b 0} :a 1 :b 2)

;; you can do the same with vectors but you need to pass the position aswell as follows
;; (assoc [] 0 5 1 5) and (assoc [0 1 2 3] 0 5 1 5)
;;           i   i                         i   i             (i = index)
;; you can put a list in at a specific index:
;; (assoc [0 1 2 3] 0 5 1 5 2 '(1 2 3 4)) and (assoc [] 0 5 1 5 2 '(1 2 3 4))
;; if you try to replace an index that does not exist it will crash.

;;------------------------------------- take, drop, take-while, and drop-while -----------------------------------------


;; the take function will take the first x values from a list or vector and put them into a list,
;; it will return all the items it has if it contains less than x even it is empty.

;; (take 3 []) and (take 3 [1 2]) and (take 3 [1 2 3 4 3 2 1 1 1]) and (take -3 [1 2 3 4 3 2 1 1 1])

;; the drop function does nearly the exact same but removes the first x values instead of taking them,
;; leaving you with what ever is left.

;; (drop 2 []) and (drop 2 [1]) and (drop 2 [1 2 3 4]) and (drop -2 [1 2 3 4 5])


;; using take and drop with the while keyword lets you loop through each item until a condition is met,
;; then take or drop what has been iterated. for example you can check for a negative value:

;; (drop-while neg? [-1 -2 3 4 5]) and (take-while neg? [-1 -2 3 4 5])

;; a good example of take and drop while in use with a anonymous function:
;; (take-while #(< (:month %) 3) food-journal) and (drop-while #(< (:month %) 3) food-journal)

;;-------------------------------------------------- RANDOM FACT -------------------------------------------------------
;; the take/drop-while function keep taking/dropping items till a condition is met, it does not check the whole seq.  ;;
;;----------------------------------------------------------------------------------------------------------------------

;; this following example uses both the take and drop-while functions to find a specific date:
;; (take-while #(< (:month %) 4) (drop-while #(< (:month %) 2) food-journal))

;; a better function for this situation would be filter and some which are covered now.

;;------------------------------------------------- filter and some ----------------------------------------------------

;; here is a line which preforms the same as the previous example but using filter:

;; (filter #(> (:month %) 2) food-journal) and (filter #(< (:human %) 5) food-journal)

;; filter simply returns each element of the sequence that match the given condition,
;; filter is a slower function than the take/drop-while function as it reads through all entries where the others do not

;; the some function just like the previous checks to see if a condition is true but will return true or nil.
;; it only checks up until the condition is met once so it is also quicker than filter, for example:

;; (some #(> (:critter %) 5) food-journal) and (some #(> (:critter %) 2) food-journal)

;; if you want to return the first entry that is true you could wrap it in and add,
;; if the first statement is true it will move onto the next which simply returns the value, for example:

;; (some #(and (> (:critter %) 3) %) food-journal) and (some #(and (> (:critter %) 5) %) food-journal)

;;----------------------------------------------- sort , sort-by and concat --------------------------------------------

;; call sort will simply put items in ascending order, numbers in size order, strings in count order, maps in val order.
;; cant apply the function to numbers and strings in one vector, function examples:

;; (sort ["s" "sss" "ss"]) , (sort [3 2 1]) , (sort {:b 1 :c 3 :a 2}) , (sort > [ 5 4 2 6 7])

;; the sort-by function works quite similar execpt you can pass a function or key to sort it with, for example:
;; (sort-by count ["aaa" "c" "bb"]), (sort-by :rank [{:rank 2} {:rank 3} {:rank 1}]), (sort-by count > ["aaa" "bb" "c"])

;; finally, concat simply appends the members of one sequence to the end of another: (concat [1 2] [3 4])

;;-------------------------------------------------- Lazy sequences ----------------------------------------------------

;; A lazy seq is a seq whose members aren’t computed until you try to access them.
;; Computing a seq’s members is called realizing the seq.
;; Deferring the computation until the moment it’s needed makes your programs more efficient,
;; and it has the surprising benefit of allowing you to construct infinite sequences.

;; here is an example of a lazy seq in use:

(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))

(defn vampire?
  [record]
  (cond
    (and (:makes-blood-puns? record) (not (:has-pulse? record)) ) record
    :else false))

(defn identify-vampire
  [social-security-numbers]
 (filter vampire? (map vampire-related-details social-security-numbers)))
;; (identify-vampire (range (count vampire-database)))

;; a good way to see a lazy seq in use is by running this:
;; (time (def mapped-details (map vampire-related-details (range 0 1000000))))

;; it works in an instance because it just saves the formula to the file not the result,
;; it will only works out the result when the data is called upon to be used for example:
;; (time (first mapped-details)) - thats why this takes so long to run as it still needs to run the math.

;;--------------------------------------------- Infinite Sequences -----------------------------------------------------

;; One cool, useful capability that lazy seqs give you is the ability to construct infinite sequences.
;; So far i've only worked with lazy sequences generated from vectors or lists however, Clojure comes with a few
;; functions to create infinite sequences. One easy way to create an infinite sequence is with repeat,
;; which creates a sequence where every entry is the argument you pass:

;; (concat (take 8 (repeat "na")) ["batman"])

;; you can also use repeatedly, its similar expect you pass a function into instead which will be called x times to
;; create the sequence, for example:

;; (take 8 (repeatedly #(rand-int 10)))

;; here in both examples the code with produce infinite sequences, we simply decide how many to use with first or take.

;; here is an example of an infinite seq in a function in use:

(defn even-numbers                                  ;; the naming is not accurate, you can run it with odd numbers.
  ([] (even-numbers 0))
  ([n] (cons n (lazy-seq (even-numbers (+ n 2))))))

;; if you call this function with out anything to limit its output it will basically crash:
;; (take 10 (even-numbers)) and (take 10 (even-numbers 6)) - this limits the function to recur only 10 times.

;;---------------------------------------------------- into and conj ---------------------------------------------------

;;One of the most important collection functions is into.
;; As you now know, many seq functions return a seq rather than the original data structure.
;; You’ll probably want to convert the return value back into the original value, and into lets you do that:

;; (map identity {:sunlight-reaction "Glitter!"}) and (into {} (map identity {:sunlight-reaction "Glitter!"}))

;; Here, the map function returns a sequential data structure after being given a map data structure,
;; and into converts the seq back into a map. This will work with other data structures as well:

;; (map identity [:garlic :sesame-oil :fried-eggs]) and (into [] (map identity [:garlic :sesame-oil :fried-eggs]))

;; if you convert any sequence into a set the duplicate entries will be removed, sets do not contain duplicate entries.
;; Because sets only contain unique values, the set ends up with just one value in it:

;; (map identity [:garlic-clove :garlic-clove]) and (into #{} (map identity [:garlic-clove :garlic-clove]))

;; you can also add two sequences together of the same type, which is the most obvious use for example:

;; (into {:favorite-animal "kitty"} {:least-favorite-smell "dog" :relationship-with-teenager "creepy"})

;; conj also adds elements to a collection, it does it differently as shows:

;; (conj [0] [1]) , (conj [0] 1) , (conj [0] 1 2 3) and (conj [] 1 2 3)

;; as you can see using conj does not put the data into a new sequence but can also put sequences into another sequence.
;; if you think about it, its in the name. you can undo that by using the function flatten if you are wondering.

;; with conj you can pass as many elements as you want but with into you have to pass a collection

;;------------------------------------------------- Function Functions -------------------------------------------------

;; To take advantage of Clojure’s ability to accept functions as arguments and return functions as values,
;; Two of Clojure’s functions, apply and partial, are especially weird because they both accept and return functions.

;; to see the use of the apply function, we start with a function which returns the max: (max 0 1 2)
;; this function though does not work on a sequence, it just returns it: (max [0 1 2 5 2])
;; this is because the function max returns the largest item passed to it, the vector is the only item.

;; to get around this we use the apply function, i do not quite understand how but it applies the given function to the
;; data with in the vector: (apply max [0 1 2])

