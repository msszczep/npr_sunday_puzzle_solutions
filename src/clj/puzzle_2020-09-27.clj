; https://www.npr.org/2020/09/27/917424844/sunday-puzzle-state-capitals

; Name a major world city with a population in the millions. Take one 
; letter in its name and move it two spots earlier in the alphabet. 
; Reading backward, you now have the name of a major restaurant chain. What is it?

(defn clean-city [city]
  (first (clojure.string/split city #",")))

(defn transform-city [city]
  (->> ""
       (clojure.string/replace city #" ")
       reverse
       (apply str)
       clojure.string/lower-case))

(def world-cities
  (->> #"\|"
       (clojure.string/split (slurp "resources/cities_with_pop_2_million_plus.txt"))
       (mapv clojure.string/trim)
       (mapv clean-city)))





