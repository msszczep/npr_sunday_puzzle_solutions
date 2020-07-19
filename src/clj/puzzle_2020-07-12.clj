

(def all-in-moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(defn two-words? [s]
  (= 2 (count (clojure.string/split s #" "))))

(defn transform [s]
  (let [ws (clojure.string/split s #" ")]
    (str (ffirst ws) (last ws))))

(def solution-set
  (map (juxt identity transform) 
       (filter two-words? all-in-moby)))

