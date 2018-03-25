; https://www.npr.org/2018/03/25/596537250/sunday-puzzle-drop-and-give-me-6

; Name a small but well-known U.S. city, followed by its 
; two-letter state postal abbreviation. This string of 
; letters, reading from left to right, spells two consecutive 
; words that name distinctive characteristics of bunnies. 
; What city is it?

(def answer
  (let [us-cities-and-states
          (->> "resources/US.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #"\t"))
               (map (juxt #(nth % 2) #(nth % 4)))
               (map (partial apply str))
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " "")))
        scrabble-words
          (->> "resources/ospd3.txt"
               slurp
               clojure.string/split-lines
               set)]
  (letfn [(partition-me [w] (->> (map #(split-at % w) (range 1 (count w)))
                                 (map #(map (partial apply str) %))))]
    (->> us-cities-and-states
         (map (partial partition-me))
         flatten
         (partition 2)
         (filter #(and (scrabble-words (first %))
                       (scrabble-words (last %))))
         (filter #(or (= "ear" (first %))
                      (= "ear" (last %))))))))

