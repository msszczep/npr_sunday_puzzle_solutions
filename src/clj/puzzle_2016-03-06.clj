;; http://www.npr.org/2016/03/06/469327504/five-letters-with-which-to-play-heres-a-puzzle-to-blow-you-away

;; Bail, Nail, and Mail are three four-letter words that differ only
;; by their first letters. And those first letters (B, N, and M) happen
;; to be adjacent on a computer keyboard. Can you think of five four-letter
;; words that have the same property — that is, they're identical except
;; for their first letters, with those first letters being adjacent on
;; the keyboard? All five words must be ones that everyone knows. Capitalized
;; words and plurals are not allowed. What words are they?

(def adjacent-letters
  (->> ["qwertyuiop" "asdfghjkl" "zxcvbnm"]
       (map #(partition 5 1 %))
       (apply concat)
       (map set)))

adjacent-letters

(def five-letter-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (filter #(= 4 (count %)))
       (group-by #(subs % 1 4))
       (filter #(<= 5 (count (val %))))
       (map last)
       (remove (fn [x] (some #(re-find #"s$" %) x)))
       (map #(map first %))
     )
)


(distinct (for [a adjacent-letters
      f five-letter-words
      :when (every? (set f) a)]
  a))

(->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
     clojure.string/split-lines
     (filter #(= 4 (count %)))
     (group-by #(subs % 1 4))
     (filter #(<= 5 (count (val %))))
     (map last)
     (remove (fn [x] (some #(re-find #"s$" %) x)))
     (filter (fn [y] (some #{\d} (map first y))))
     (filter (fn [y] (some #{\f} (map first y))))
     (filter (fn [y] (some #{\g} (map first y))))
     (filter (fn [y] (some #{\h} (map first y))))
     (filter (fn [y] (some #{\s} (map first y))))
     )




; dear fear gear hear sear
