

(def homophones
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"\s" 2))
       (group-by second)
       (filter (comp (partial < 1) count second))
       (map (comp (partial map first) second))))

(defn filter-find [])
