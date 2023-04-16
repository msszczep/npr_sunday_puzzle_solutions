; https://www.npr.org/2023/04/16/1170262935/sunday-puzzle-a-city-scramble

; Think of a common 8-letter word, in which the first three letters spell 
; a word, and the fifth, sixth, and seventh letters also spell a word. 
; These two little words mean the same thing. The fourth letter, when 
; rotated 180°, becomes the eighth letter. What word is this?

; d -> p, m -> w, n -> u, q -> b, 
; dpmwnuqb

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def threes (filter-word-count 3))
(def eights (filter-word-count 8))

(defn transform-word [seven-letter-word letters-to-remove]
  "letters-to-remove is assumed to be a set of numbers"
  (->> seven-letter-word
       (map-indexed (fn [idx itm] [(inc idx) itm]))
       (remove (comp letters-to-remove first))
       (map second)
       (apply str)))

; (#{\d \p \q \b \m \w \n \u} (nth eight 3))

(def solution
  (for [eight eights
        :when (and (threes (transform-word eight #{4 5 6 7 8}))
                   (threes (transform-word eight #{1 2 3 4 8}))
                   (#{#{\d \p} #{\b \q} #{\m \w} #{\n \u}} (set (map char (str (nth eight 3) (nth eight 7)))))
                   )]
    [eight
     (threes (transform-word eight #{4 5 6 7 8}))
     (threes (transform-word eight #{1 2 3 4 8}))]))
