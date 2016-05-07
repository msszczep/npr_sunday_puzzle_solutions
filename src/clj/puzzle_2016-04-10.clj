;; http://www.npr.org/2016/04/10/473649171/rearrange-the-letters-in-these-names-to-solve-this-puzzle

;; Name something in eight letters that's usually bought in pairs.
;; Change the second letter to the letter two spaces later in the alphabet,
;; and you'll get a new word that names something else that's usually bought
;; in pairs. Both words are plurals. What are they?


(->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
     clojure.string/split-lines
     (filter #(= 8 (count %)))
     (group-by #(str (subs % 0 1) (subs % 2)))
     (filter #(< 2 (count (val %))))
     (map last)
     (filter (fn [x] (every? #(re-find #"s$" %) x)))
;     count
    )
