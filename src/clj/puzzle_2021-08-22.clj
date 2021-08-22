; https://www.npr.org/2021/08/22/1029993143/sunday-puzzle-connect-the-words

; Take the name of a major American city. Move one of its letters 
; three spaces later in the alphabet. Embedded in the resulting string 
; of letters, reading left to right, is a cardinal number. Remove that 
; number, and the remaining letters, reading left to right, spell an 
; ordinal number. What city is it, and what are the numbers?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))
       (mapv clean-up-word)))

(filter #(re-find #"th$" %) us-cities)
