;; http://www.npr.org/2015/12/20/460421632/yule-never-guess-the-theme-to-this-weeks-song-filled-puzzle

;; Think of four common six-letter words that all end in the same five letters, in the same order.
;; And the first letters of these four words are consecutive consonants in the alphabet
;; (like B, C, D, F). No other common six-letter words end with these five letters. What are the words?


(def fours (set (map clojure.string/join (partition 4 1 "bcdfghjklmnpqrstvwxz"))))

fours

(def six-letter-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (filter #(= 6 (count %)))
       (group-by #(subs % 1 6))
       (filter (fn [[_ v]] (= 4 (count v))))
       (filter (fn [[_ v]] (fours (clojure.string/join (map first v)))))
  ))

six-letter-words


