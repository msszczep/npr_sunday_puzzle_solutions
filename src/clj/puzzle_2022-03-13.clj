; https://www.npr.org/2022/03/13/1085833046/sunday-puzzle-word-sandwiches

; Think of two four-letter words that complete the phrase "___ in the ___." 
; Move the first letter of the second word to the start of the first word. 
; You'll get two synonyms. What are they?

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def threes
  (set (filter (comp (partial = 3) count) ospd)))

(def fours
   (filter (comp (partial = 4) count) ospd))

(def fives
   (filter (comp (partial = 5) count) ospd))

(defn turn-to-three [w]
  (apply str (rest w)))

(take 20 (mapv (juxt identity turn-to-three) fours))

(take 20 (filter (comp threes turn-to-three) fours))

(sort (filter (comp threes turn-to-three) fours))









