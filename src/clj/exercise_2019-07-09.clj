; What combination of five English letters delivers the most English anagrams?

(def result
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 5) count))
       (group-by frequencies)
       (sort-by (comp count second))
       reverse
       (take 15)
       (map second)
       (map (juxt identity count))))


