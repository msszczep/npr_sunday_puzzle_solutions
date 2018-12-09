; https://www.npr.org/2018/12/02/672656517/sunday-puzzle-2-words

; Think of a common 7-letter word. Drop its second letter, and 
; you'll get a 6-letter word that does not rhyme with the first. 
; Alternatively, you can drop the third letter from the 7-letter 
; word to get a 6-letter word that doesn't rhyme with either of 
; the first two. Further, you can drop both the second and third 
; letters from the 7-letter word to get a 5-letter word that doesn't 
; rhyme with any of the others. What words are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def fives (filter-word-count 5))
(def sixes (filter-word-count 6))
(def sevens (filter-word-count 7))


(defn transform-word [seven-letter-word letters-to-remove]
  "letters-to-remove is assumed to be a set of numbers"
  (->> seven-letter-word
       (map-indexed (fn [idx itm] [(inc idx) itm]))
       (remove (comp letters-to-remove first))
       (map second)
       (apply str)))


(def solution
  (for [seven sevens
        :when (and (sixes (transform-word seven #{2}))
                   (sixes (transform-word seven #{3}))
                   (fives (transform-word seven #{2 3})))]
    [seven
     (sixes (transform-word seven #{2}))
     (sixes (transform-word seven #{3}))
     (fives (transform-word seven #{2 3}))]))
