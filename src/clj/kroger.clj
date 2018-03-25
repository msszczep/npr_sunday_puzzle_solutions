; "try age" "Kroger" -> "K roger"; "Fatone" -> "fat one"; "Capone" -> "cap one"

(let [scrabble-words
       (->> (slurp "resources/ospd3.txt")
            clojure.string/split-lines
            set)]
  (letfn [(partition-me [w] (->> (map #(split-at % w) (range 1 (count w)))
                                 (map #(map (partial apply str) %))))]
    (->> (slurp "resources/cmudict-0.7b.txt")
         clojure.string/split-lines
         (map #(clojure.string/split % #" "))
         (map first)
         (map clojure.string/lower-case)
         (map (partial partition-me))
         flatten
         (partition 2)
         (filter #(and (scrabble-words (first %))
                       (scrabble-words (last %)))))))

