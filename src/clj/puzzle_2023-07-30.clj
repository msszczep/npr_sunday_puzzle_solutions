; https://www.npr.org/2023/07/30/1190970464/sunday-puzzle-let-the-categories-guide-you

; Name a well-known U.S. city in nine letters. Change the third and fifth 
; letters to get the name of a beverage. What is it?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))))

(def nines
  (filter (comp (partial = 9) count) (map clean-up-word us-cities)))
