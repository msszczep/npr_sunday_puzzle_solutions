;; Trivia calendar: What is the longest English word in which the letters
;; appear in alphabetical order?

(letfn [(is-alphabetical-order?
          [word]
          (let [w (clojure.string/lower-case word)]
            (= (map char w) (sort w))))]
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ni2.txt")
       clojure.string/split-lines
       (filter is-alphabetical-order?)
       (sort-by (comp (partial * -1) count))
       (take 10)))



