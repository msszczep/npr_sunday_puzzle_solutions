; https://www.npr.org/2018/04/29/606172433/sunday-puzzle-the-same-but-different

; Name a famous player in the Baseball Hall of Fame. Take a letter out of the 
; last name and move it into the first name. The result will name something you 
; might see at a concert. What is it?

(def answer
  (let [players
          (->> "resources/baseball_hof.txt"
               slurp
               clojure.string/split-lines
               (filter (partial re-find #"Player"))
               (map #(-> %
                         (clojure.string/split #",")
                         second
                         (clojure.string/split #"\\")
                         first
                         clojure.string/lower-case
                         (clojure.string/split #" "))))
        scrabble-words
          (->> "resources/ospd3.txt"
               slurp
               clojure.string/split-lines
               set)]
    (letfn [(get-candidate-words [surname]
              (let [surname-to-use (map-indexed vector surname)]
                (letfn [(remove-entry [n]
                          (remove (comp (partial = n) first)
                                  surname-to-use))]
                    (set (map (comp (partial apply str)
                                    (partial map second))
                              (map remove-entry (range (count surname-to-use))))))))]
      (for [player players
            :let [candidate-words ((comp get-candidate-words last) player)
                  words (clojure.set/intersection scrabble-words candidate-words)]
            :when (not-empty words)]
        [player words]))))
