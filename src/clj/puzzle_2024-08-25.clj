; https://www.npr.org/2024/08/24/nx-s1-5086401/sunday-puzzle-two-of-the-same

; The word NONUNION has four N's and no other consonant. 
; What famous American of the past -- first and last names, 
; 8 letters in all -- has four instances of the same consonant 
; and no other consonant?

; time more enwiki-20200820-all-titles-clean.txt | egrep '^[A-Za-z_]{9}$' > eights.txt

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def eights
  (->> "resources/eights.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #"_"))
       (filter (comp (partial = 2) count))))

(defn has-one-consonant? [e]
  (->> e
       (apply concat)
       (map clojure.string/lower-case)
       (map (partial apply char))
       (filter #{\b \c \d \f \g \h \j \k \l \m \n \p \q \r \s \t \v \w \x \y \z})
       distinct
       count
       (= 1)))

(def candidates
  (filter has-one-consonant? eights))


