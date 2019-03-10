; https://www.npr.org/2019/03/10/701952153/sunday-puzzle-solve-this-case

; Think of a 4-letter word for something commonly 
; seen in the winter. Write it in lowercase letters. 
; Turn it upside, and you'll name a device you use 
; with this thing. What is it?

(def scrabble-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def all-cmu-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^A-Z]" ""))))


(def letters (set "bdlmnopqsuwxz"))

(def solution 
  (->> scrabble-words
       (filter (comp (partial = 4) count))
       (filter (partial every? letters))
       sort))

(def solution2 
  (->> all-cmu-words
       (map clojure.string/lower-case) 
       (filter (comp (partial = 4) count))
       (filter (partial every? letters))
       sort))


