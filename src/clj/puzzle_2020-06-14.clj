; https://www.npr.org/2020/06/14/876570406/sunday-puzzle-replacement-ids

; Think of a five-letter word. Change the first letter to the next letter 
; of the alphabet, and you'll get a new word that doesn't share any sounds 
; with the first one. Then change its first letter to the next letter of the 
; alphabet, and you'll get a third word that doesn't share any sounds with either 
; of the first two. What words are these?

(def five-letter-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 5) count))))

(def letters-to-numbers
  {\a 1, \b 2, \c 3, \d 4, \e 5, \f 6, \g 7,
   \h 8, \i 9, \j 10, \k 11, \l 12, \m 13, \n 14,
   \o 15, \p 16, \q 17, \r 18, \s 19, \t 20, \u 21,
   \v 22, \w 23, \x 24, \y 25, \z 26})

(defn are-numbers-consecutive? [s]
  (= s [(first s) (inc (first s)) (+ 2 (first s))]))

(defn are-first-letters-consecutive? [words]
  (->> words
       (map first)
       (map letters-to-numbers)
       (partition 3 1)
       (map are-numbers-consecutive?)
       (some true?)))

(defn get-consecutive-triplets [s]
  (->> s
       (partition 3 1)
       (filter are-first-letters-consecutive?)))

(def solution 
  (->> five-letter-words
       (group-by rest)
       (filter (comp (partial < 3) count second))
       (mapv second)
       (filter are-first-letters-consecutive?)
       (mapv get-consecutive-triplets)))

