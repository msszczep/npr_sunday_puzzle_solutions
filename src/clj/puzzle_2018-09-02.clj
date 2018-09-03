; https://www.npr.org/2018/09/02/643681587/sunday-puzzle-other-l-words

; The name of the film director David Lynch conceals the word AVIDLY 
; in consecutive letters, spanning his first and last names. Can you 
; think of a famous film director whose first and last names conceal 
; a 6-letter name of car, past or present, in consecutive letters?

(def answer
  (let [film-directors
          (->> "resources/film_directors_clean.txt"
               slurp
               clojure.string/split-lines
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " "")))
        car-names
          (->> "resources/car_names.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/replace % #" " ""))
               (filter (comp (partial = 6) count))
               (map clojure.string/lower-case)
               set)]
      (remove nil? 
        (for [fd film-directors]
          (let [sixes (map #(apply str %) (partition 6 1 fd))]
            (when (not= '() (filter car-names sixes))
              [fd (filter car-names sixes)]))))))

