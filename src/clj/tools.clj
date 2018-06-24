(def words
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))


(defn words [w1 w2]
  (->> (interleave w1 w2)
       (partition 2)
       (remove #(= (first %) (last %)))
       count
       (= 1)))
