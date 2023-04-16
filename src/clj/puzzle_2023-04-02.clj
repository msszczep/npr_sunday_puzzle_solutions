(def ospd-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 8) count))
        set))

(defn get-substring [w]
  (let [[a b c d e f g h] (map char w)]
    (vector (set (apply str [b d])) (apply str [a c e f g h]))))

(defn answer
  (->> ospd-words
       (group-by get-substring)
       (filter (comp (partial < 1) count val))))


