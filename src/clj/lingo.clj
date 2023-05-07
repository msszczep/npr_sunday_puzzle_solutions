(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def fives (filter-word-count 5))
(def sixes (filter-word-count 6))

(def lingo-five (group-by first fives))

(def lingo-five-count-by-first-letter 
  (mapv #(vector (key %) (count (val %))) lingo-five))

(reverse (sort-by second lingo-five-count-by-first-letter))

(def all-fives-frequencies (frequencies (apply str fives)))

(reverse (sort-by val all-fives-frequencies))

(def fives-percentages
  (reverse (sort-by last (mapv #(vector (key %) (val %) (float (/ (val %) (count (apply str fives))))) all-fives-frequencies))))

(def scoresheet 
  (apply hash-map (flatten (map (juxt first last) fives-percentages))))

(defn calc-score [word]
  (->> word
       (map char)
       (map scoresheet)
       (apply +)
       (* 100)
       Math/floor
       int))

; highest scores
(take 20 (reverse (sort-by second (map (juxt identity calc-score) fives))))

; calculate highest score for each starting letter

(group-by first (mapv (juxt first identity calc-score) fives))
