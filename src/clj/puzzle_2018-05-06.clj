; https://www.npr.org/2018/05/06/607707509/sunday-puzzle-in-d-mood-for-a-game

; Name a certain kind of criminal. Drop the first two letters and the 
; last letter of the word, and you'll name a country. What is it?


(def answer
  (let [words
          (->> "resources/ni2.txt"
               slurp
               clojure.string/split-lines)
        countries
          (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))
               (map first)
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " ""))
               set)]
    (for [word words
          :let [t-word (subs word (if (> (count word) 2) 2 0)
                                  (if (> (count word) 1)
                                      (dec (count word))
                                      (count word)))
                country-subset (clojure.set/intersection countries 
                                                         #{t-word})]
          :when (contains? countries t-word)]
      [word t-word])))
