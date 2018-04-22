; https://www.npr.org/2018/04/22/604475596/sunday-puzzle-lets-play-ball

; Take the name of a famous film director. Drop the first letter of 
; this person's first name and you'll name a fish. Read the last name 
; backward and you'll name another fish. What film director is it?

(def answer
  (let [film-directors
          (->> "resources/film_directors_clean.txt"
               slurp
               clojure.string/split-lines
               (map clojure.string/lower-case)
               (map #(clojure.string/split % #" ")))
        scrabble-words
          (->> "resources/ospd3.txt"
               slurp
               clojure.string/split-lines
               set)]
    (for [fd film-directors
          :when (and (contains? scrabble-words 
                                ((comp (partial apply str) rest first) fd))
                     (contains? scrabble-words
                                ((comp (partial apply str) reverse last) fd)))]
      fd)))

