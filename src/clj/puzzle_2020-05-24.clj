; https://www.npr.org/2020/05/24/861562947/sunday-puzzle-categories-of-categories

; Think of a well-known European city in seven letters. If you remove the third 
; letter, you'll get a two-word phrase describing what you must do to win a race. 
; If instead you remove the fourth letter, you'll get a two-word phrase describing 
; what you can't do to win a race. What's the city?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Za-z]" "")))

(def cities
  (->> "resources/european_cities.csv"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"\t"))
       (mapv (comp clean-up-word first))
       (filter (comp (partial = 7) count))))

(defn european-juxt [city]
  (vector city 
          (str (subs city 0 2) (subs city 3))
          (str (subs city 0 3) (subs city 4))))

(clojure.pprint/pprint (sort (mapv european-juxt cities)))
