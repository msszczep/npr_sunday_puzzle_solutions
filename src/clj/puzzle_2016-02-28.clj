;; http://www.npr.org/2016/02/28/468417481/and-this-puzzles-winner-is

;; What two 8-letter terms in math are anagrams of each other? One word
;; is from geometry, the other is from calculus. What words are they?


(->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
     clojure.string/split-lines
     (filter #(= 8 (count %)))
     (group-by frequencies)
     (filter #(<= 2 (count (val %))))
     (map last)
     (filter (fn [x] (some #(re-find #"angle" %) x)))
    )

