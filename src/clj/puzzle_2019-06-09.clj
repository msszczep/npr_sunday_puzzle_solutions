; https://www.npr.org/2019/06/09/730824490/sunday-puzzle-lost-arts

; Think of a famous actor, first and last names, that together 
; contain each of the five vowels (A, E, I, O, and U) exactly once. 
; Add an M and rearrange the result to get a famous writer, also first 
; and last names. Who are these famous people?

(def actors
   (->> "resources/actors.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))))

(def writers
   (->> "resources/writers.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))))

(defn all-vowels-exactly-once? [s]
  (let [f (frequencies s)]
    (and (= 1 (get f \a))
         (= 1 (get f \e))
         (= 1 (get f \i))
         (= 1 (get f \o))
         (= 1 (get f \u)))))


(def solution 
  (for [a (filter all-vowels-exactly-once? actors)
        :let [w (filter #(= (frequencies (str a "m")) (frequencies %)) 
                        writers)]
        :when (not (empty? w))]
    [a w]))



