; https://www.npr.org/2021/10/17/1046742364/sunday-puzzle-dont-lu-se-this-puzzle

; Name a famous actress (8,6). Change the next-to-last letter of her first name 
; to an S. Then reverse the order of the last three letters, and you'll name a 
; famous ruler. The actress's last name is an anagram of where you would find 
; this ruler. Who is the actress and the ruler?

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def who2
  (->> "resources/candidates_2021-10-17.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       sort
       (mapv #(clojure.string/split % #"_"))))

(def female-first-names
  (->> "resources/female_first_names_upper.txt"
       slurp
       clojure.string/split-lines
       (mapv (comp (partial apply str) butlast clojure.string/lower-case))
       set))

(def solution-set
  (->> who
       (filter (comp (partial = 2) count))
       (filter (comp (partial = 6) count first))
       (filter (comp (partial = 8) count last))))

(defn transform [[fname lname]]
  (let [[a b c d e f g h] (map char fname)
        [i j k l m n] (map char lname)]
    [(apply str [a b c d e f \s h])
     (apply str [i j k n m l])]))

(def solution
  (let [solution-set (set who2)]
    (mapv (juxt identity transform) (filter (comp female-first-names first) who2))))

(def solution2
  (mapv (juxt identity transform) who2))


