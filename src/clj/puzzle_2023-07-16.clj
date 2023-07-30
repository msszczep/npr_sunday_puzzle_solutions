

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(map concat (filter (comp (partial = 2) count) who))

 (filter (partial re-find #"l$") (map first who))

(filter (comp (partial = 12) count) (map (partial apply str) (map concat (filter (comp (partial = 2) count) who))))

