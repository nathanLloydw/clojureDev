(ns projects.regularExpressions)

(use '[imports.matcher :refer :all])
(use '[imports.trace :refer :all])

;; all regular expressions take place inside quotes after a hashtag like follows: #"regular expressions"

;; the best way to use regular expressions is with some built in functions for example, re-matches:
(re-matches #"abc" "abc")
(re-matches #"abc" "bcd")

;;re-matches simply checks if the whole string matches the following
;;to match a sub string you are to use the function re-find, for example:
(re-find #"ss" "Loch Ness")
(re-find #"sss" "Loch Ness")
(re-find #"s+" "Loch Nesssssss")    ;; here in the regex the '+' finds the letters x times over

;;re-find will only return the first pattern they find, if you are looking through a huge string,
;;you should use the re-seq function as it will find the pattern as many times as it exists:
(re-seq #"s+" "mississippi")
(re-seq #"i+" "mississippi")
(re-seq #"j+" "mississippi")

;;to replace what you are looking for with another string you use the replace function as follows:
(clojure.string/replace "mississippi" #"i" "obb")
(clojure.string/replace "mississippi" #"i" "o")
(clojure.string/replace "mississippi" #"i.." "obb")   ;;the '..' here specifies the characters to be replaced

;;you can split a string using regex dependent on a pattern, for example:
(clojure.string/split "This is a string    that I am splitting." #"\s+")

;;regex tokens:
;; #"a"      - the single character a
;; #"ab"     - the single word ab, etc.
;; #"[a-z]"    - any character in the range of a-z
;; #"[^a-z]"   - any character not in the range of a-z
;; #"[a-zA-Z]" - any character in the range of a-z and A-Z
;; #"."      - any single character
;; #"\s"     - any whitespace character
;; #"\S"     - any non white space character
;; #"\d"     - any digit
;; #"\D"     - any non digit
;; #"(.)"    - anything enclosed
;; #"(a|b)"  - either a or b
;; #"a+"     - one or more of a
;; #"a{3}"   - exactly 3 of a
;; #"a{3,}"  - exactly 3 or more of a
;; #"a{3,6}" - between 3 and six of a
;; #"^"      - start of string
;; #"$"      - end of string