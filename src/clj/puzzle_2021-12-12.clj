; https://www.npr.org/2021/12/12/1063396840/sunday-puzzle-what-rhymes-with-tourist-site

; Think of a major U.S. city in two words. Insert an L in the exact middle of the 
; second word. Now read the first word forward and the second word backward, and 
; you'll name two things associated with this time of year. What are they?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(def cities
  (->> "resources/us_cities_big_list.txt"
       slurp
       clojure.string/split-lines))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))))

(defn filter-city [city]
  (let [r (clojure.string/split city #" ")]
    (and (= 2 (count r))
         (even? (count (second r))))))

(defn transform-word [w]
  (
    (reverse (clean-up-word w))))
