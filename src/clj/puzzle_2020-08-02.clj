; https://www.npr.org/2020/08/02/898127596/sunday-puzzle-capital-scramble

; Think of a famous living American whose first and last names have a total 
; of eight letters — all different. Five of these letters are consecutive 
; in the alphabet. The remaining three can be rearranged to spell a woman's 
; nickname. What famous American is this?

(defn clean-up [name]
  (clojure.string/lower-case (clojure.string/replace name #"[^A-Za-z]" "")))

(def people
   (->> "resources/enwiki-20200720-all-titles-clean2.txt"
        slurp
        clojure.string/split-lines))

(defn distinct-letters? [name]
  (= 8 (count (distinct name))))

(defn consec-letters? [name]
  (let [consec (->> "abcdefghijklmnopqrstuvwxyz"
                    (partition 5 1)
                    (map (partial into []))
                    set)]
    (->> name
         sort
         (partition 5 1)
         (map consec)
         (some (comp not nil?)))))

(def filtered-people
  (->> people
       (mapv (juxt identity clean-up))
       (filter (comp (partial = 8) count second))
       (filter (comp distinct-letters? second))
       (filter (comp consec-letters? second))))
