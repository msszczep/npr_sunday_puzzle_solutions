; https://www.npr.org/2021/05/16/997155866/sunday-puzzle-common-ground

; Name a popular singer — first and last names. Change one letter 
; to a "P" and read the result backward. You'll get what many people 
; do around this singer. Who is it?

(def words
  (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        set))

(def candidates
  (->> "resources/candidates3.txt"
       slurp
       clojure.string/split-lines))

(defn transform-fc [s]
  (apply str (reverse (clojure.string/replace (clojure.string/lower-case s) #"_" ""))))

(defn change-one-let [s n]
  (apply str
    (for [i (range (count s))]
      (if (= i n)
        \p
        (nth s i)))))

(defn transform-name [s]
  (-> s
      transform-fc
      count
      range
      ((partial map (partial change-one-let (transform-fc s))))))

(defn find-ok-words [[_ cands]]
  (->> cands
       set
       (clojure.set/intersection words)
       count
       pos?))

(def answer
  (->> candidates
       (mapv (juxt identity transform-name))
       (filter find-ok-words)))
