; https://www.npr.org/2023/09/24/1201320604/sunday-puzzle-back-to-class

; Name a major U.S. city in two words. Change the first letter of the first 
; word and the next-to-last letter of the second word. Then rearrange all 
; the letters to name the people who live in this city. What city is it? 

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))))

(defn filter-city [city]
  (let [r (clojure.string/split city #" ")]
    (= 2 (count r))))

(def t
  (filter filter-city us-cities))

