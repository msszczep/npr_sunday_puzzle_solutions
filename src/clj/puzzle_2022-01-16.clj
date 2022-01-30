; https://www.npr.org/2022/01/16/1073335623/sunday-puzzle-hitting-the-right-key

; What three common five-letter nicknames have the same last four letters 
; and alphabetically consecutive initial letters? Or to put it another way, 
; think of three common five-letter nicknames that have alphabetically 
; consecutive initial letters and the same last four letters. Which common 
; nicknames are these?

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 5) count))
       set))

(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
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
  (->> ni2
       (group-by rest)
       (filter (comp (partial < 3) count second))
       (mapv second)
       (filter are-first-letters-consecutive?)
       (mapv get-consecutive-triplets)))

