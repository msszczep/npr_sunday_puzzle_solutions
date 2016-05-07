;; http://www.npr.org/2016/04/03/472825113/got-2-words-in-the-same-category-its-rhymin-time

;; Take the word EASY: Its first three letters — E, A and S — are the fifth, first,
;; and nineteenth letters, respectively, in the alphabet. If you add 5 + 1 + 19,
;; you get 25, which is the value of the alphabetical position of Y, the last letter of EASY.

;; Can you think of a common five-letter word that works in the opposite way — in which
;; the value of the alphabetical positions of its last four letters add up to the value
;; of the alphabetical position of its first letter?

(let [letter-to-position
      (->> (map-indexed (fn [i j] [j (inc i)]) "abcdefghijklmnopqrstuvwxyz")
           (apply concat)
           (apply array-map))
      position-to-letter (clojure.set/map-invert letter-to-position)]
  (letfn [(get-positions-for-letters [letters]
            (map letter-to-position letters))
          (get-letters-for-positions [positions]
            (map position-to-letter positions))]
    (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
         clojure.string/split-lines
         (filter (comp (partial = 5) count))
         (map get-positions-for-letters)
         (filter (fn [s] (= (first s) (apply + (rest s)))))
         (map get-letters-for-positions)
         (map (partial apply str)))))

