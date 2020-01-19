
(def name-to-use "katiecourwc")

;(def name-to-use "dwanesawyer")

(def words
  (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)))

(def words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #" ") first))
       (map #(clojure.string/replace % #"[^A-Z]" ""))
       (map clojure.string/lower-case)))

(defn filter-word-count [n]
  (set (filter (comp (partial = n) count) words)))

(def threes (filter-word-count 3))
(def fours (filter-word-count 4))
(def fives (filter-word-count 5))
(def sixes (filter-word-count 6))
(def sevens (filter-word-count 7))
(def eights (filter-word-count 8))

(defn subanagram? [base compare]
  (let [base-frequencies
         (merge 
           (zipmap "abcdefghijklmnopqrstuvwxyz" (repeat 26 0))
           (frequencies base))
        compare-frequencies 
         (frequencies compare)]
    (every? #(<= (compare-frequencies %)
                 (base-frequencies %)) 
            (keys compare-frequencies))))

(def filtered-threes (filter (partial subanagram? name-to-use) threes))

(def filtered-fours (filter (partial subanagram? name-to-use) fours))

(def filtered-fives (filter (partial subanagram? name-to-use) fives))

(def filtered-sixes (filter (partial subanagram? name-to-use) sixes))

(def filtered-sevens (filter (partial subanagram? name-to-use) sevens))

(def filtered-eights (filter (partial subanagram? name-to-use) eights))

(def solution-space
  (for [five filtered-fives
        six filtered-sixes
        :when (= kc (frequencies (str five six)))]
    [five six]))

(def solution-space2
  (for [four filtered-fours
        seven filtered-sevens
        :when (= kc (frequencies (str four seven)))]
    [four seven]))

(def solution-space3
  (for [three filtered-threes
        eight filtered-eights
        :when (= kc (frequencies (str three eight)))]
    [three eight]))
