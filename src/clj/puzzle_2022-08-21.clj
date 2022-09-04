; https://www.npr.org/2022/08/21/1118632912/sunday-puzzle-double-down

; Think of an eight-letter noun composed phonetically of two consecutive 
; names traditionally given to girls. Remove the sixth letter and rearrange 
; the result. You'll get an event where you might hear the thing named by 
; the original noun. What words are these?

(def eight-letter-nouns
  (->> "resources/eight-letter-nouns.txt"
        slurp
        clojure.string/split-lines))

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def sevens (filter-word-count 7))
(def eights (filter-word-count 8))

(defn transform-word [w letters-to-remove]
  "letters-to-remove is assumed to be a set of numbers"
  (->> w
       (map-indexed (fn [idx itm] [(inc idx) itm]))
       (remove (comp letters-to-remove first))
       (map second)
       (apply str)))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(def solution
  (for [eight eight-letter-nouns
        :let [fwords (filter #(anagram? % (transform-word eight #{6})) sevens)]
        :when (not (empty? fwords))]
    [eight fwords]))
