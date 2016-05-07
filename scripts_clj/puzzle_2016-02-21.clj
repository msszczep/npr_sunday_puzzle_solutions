;; http://www.npr.org/2016/02/21/467515215/the-phrase-or-name-is-familiar-try-this-puzzle-and-see-what-they-are

;; Find three 8-letter words that are identical in spelling except for the 4th letter.
;; Each word has a G that's pronounced differently in all three words.  What are the words?


(->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
     clojure.string/split-lines
     (filter #(= 8 (count %)))
     (filter #(some #{\g} %))
     (group-by #(str (subs % 0 3) (subs % 4)))
     (filter #(< 2 (count (val %))))
     (map last)
     (remove (fn [x] (every? #(re-find #"ings?$" %) x)))
     )

(subs "abcdef" 1)
