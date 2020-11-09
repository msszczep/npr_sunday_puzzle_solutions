; https://www.npr.org/2020/11/01/929855461/sunday-puzzle-rhyme-city

; Name a well-known U.S. city in two words (5,3).  Change the first letter of the second
; word to name a popular rock group.  Who is it?

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))
       (map clojure.string/lower-case)
       (filter (partial re-find #"^[a-z]{5} [a-z]{3}$"))))






