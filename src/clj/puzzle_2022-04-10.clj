; https://www.npr.org/2022/04/10/1091879977/sunday-puzzle-w-first-and-last-its-the-law

; Think of a 5-letter word with an "L" that is pronounced. Add a letter 
; at the start to get a 6-letter word in which the "L" is silent. Then 
; add a new letter in the fifth position to get a 7-letter word in which 
; the "L" is pronounced again. What words are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def fives (filter (partial re-find #"l") (filter-word-count 5)))
(def sixes (filter-word-count 6))
(def sevens (filter-word-count 7))

(defn has-leading-hooks? [five sixes]
  (filter (partial re-find (re-pattern (str five "$"))) sixes))

(def filtered-sixes (remove empty? (mapv #(has-leading-hooks? % sixes) fives)))

(defn find-filtered-sevens [six sevens]
  (let [first-part (subs six 0 4)
        second-part (subs six 4)]
    (filter (partial re-find (re-pattern (str first-part "[a-z]" second-part))) sevens)))

(def filtered-sevens (remove empty? (mapv #(find-filtered-sevens % sevens) filtered-sixes)))

