; https://www.npr.org/2019/11/17/780092243/sunday-puzzle-words-that-end-in-llo

; The city of Mobile, Ala., has the interesting property that the name of the city 
; has exactly the same consonants as its state (M, B, and L), albeit in a different 
; order. What is the next-largest U.S. city for which this is true?

(def d
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"\t"))
       (map (juxt (comp clojure.string/lower-case second) 
                  (comp clojure.string/lower-case #(nth % 2))))))


(defn get-consonants [s]
  (->> s
       (map char)
       (filter #{\b \c \d \f \g \h \j \k \l \m 
                 \n \p \q \r \s \t \v \w \x \y \z})
       sort))


(def solution
  (filter (fn [[city state]] (= (get-consonants city)
                                (get-consonants state))) d))

