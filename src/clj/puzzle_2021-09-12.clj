; https://www.npr.org/2021/09/12/1036328447/sunday-puzzle-names-and-fame

; Think of two famous singers with the same five-letter first name. 
; Take the last name of one of these singers. Switch the second and 
; third letters. Then advance the resulting first and third letters 
; of each to the next letter in the alphabet. The result will be the 
; last name of the other singer. What singers are these?

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def who2
  (->> "resources/candidates_2021-09-12.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       sort
       (mapv #(clojure.string/split % #"_"))))

(def solution-set
  (->> who
       (filter (comp (partial = 2) count))
       (filter (comp (partial = 5) count last))
       (group-by last)
       (filter (comp (partial < 1) count last))))

(take 5 (map (juxt first (comp set (partial mapv last) last)) (filter (comp (partial < 1) count last) (group-by first who2))))

(defn make-new-name [n]
  (let [next-letter {\a \b, \b \c, \c \d, \d \e, \e \f, \f \g, \g \h,
         \h \i, \i \j, \j \k, \k \l, \l \m, \m \n, \n \o,
         \o \p, \p \q, \q \r, \r \s, \s \t, \t \u, \u \v,
         \v \w, \w \x, \x \y, \y \z}
        [a b c & d] (map char n)]
          (apply str (flatten [(next-letter a) c (next-letter b) d]))))

(defn check-please [[_ name-set]]
  (let [transformed (set (mapv make-new-name name-set))]
    (not (empty? (clojure.set/intersection name-set transformed)))))

(filter check-please (map (juxt first (comp set (partial mapv last) last)) (filter (comp (partial < 1) count last) (group-by first who2))))

(defn show-names [[fname name-set]]
  (let [transformed (set (mapv make-new-name name-set))]
    [fname (clojure.set/intersection name-set transformed)]))

(map first (filter check-please (map (juxt first (comp set (partial mapv last) last)) (filter (comp (partial < 1) count last) (group-by first who2)))))

(map show-names (filter check-please (map (juxt first (comp set (partial mapv last) last)) (filter (comp (partial < 1) count last) (group-by first who2)))))
