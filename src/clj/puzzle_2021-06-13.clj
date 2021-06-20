; https://www.npr.org/2021/06/13/1005890820/sunday-puzzle-7-famous-letters

; This week's challenge comes from listener Sandy Weisz, of Chicago. Name 
; a famous woman in American history with a three-part name. Change one 
; letter in her first name to a double letter. The resulting first and 
; second parts of her name form the first and last names of a famous 
; athlete. And the last part of the woman's name is a major rival of 
; that athlete. Who are these people?


(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

; egrep '^[A-Z][a-z]+_[A-Z][a-z]+_[A-Z][a-z]+$' all_en_wikipedia_titles_20210420.txt > candidates_three_names.txt
; egrep '^[A-Z][a-z]+_[A-Z][a-z]+$' all_en_wikipedia_titles_20210420.txt > candidates_two_names.txt

(def three
  (->> "resources/candidates_three_names.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

(def two
  (->> "resources/candidates_two_names_dedup.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines))

(defn has-double-letter? [s]
  (contains? (set (mapv (comp count set) (partition 2 1 s))) 1))

