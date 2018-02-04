; "try age" "Kroger" -> "K roger"; "Fatone" -> "fat one"; "Capone" -> "cap one"

(let [scrabble-words
       (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
            clojure.string/split-lines
            set)]
  (letfn [(partition-me [w] (->> (map #(split-at % w) (range 1 (count w)))
                                 (map #(map (partial apply str) %))))]
    (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
         clojure.string/split-lines
         (map #(clojure.string/split % #" "))
         (map first)
         (map clojure.string/lower-case)
         (map (partial partition-me))
         flatten
         (partition 2)
         (filter #(and (scrabble-words (first %))
                       (scrabble-words (last %)))))))

