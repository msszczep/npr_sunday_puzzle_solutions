; https://www.npr.org/2019/02/24/696983863/sunday-puzzle-oscars-night

; I'm thinking of a well-known U.S. natural landmark. Take the 
; two-word name of its location. Then change the first letter of 
; the second word to the immediately previous letter of the alphabet, 
; and you'll get another description of the landmark's location. 
; What's the landmark, and what are the two descriptions of its location?

(def all-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def letters "abcdefghijklmnopqrstuvwxyz")

(def previous-letter (zipmap (rest letters) letters))

(defn transformed-ok? [w]
  (->> w
       rest
       (cons (previous-letter (first w)))
       (apply str)
       all-words
       count
       (= (count w))))

(def filtered-words (filter transformed-ok? all-words))
