; https://www.npr.org/2021/11/21/1057517618/sunday-puzzle-wordy-to-worldly

; Name a country of six or more letters. Change two letters in it to name 
; the resident of another country's capital.

(def country-data
  (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))))

(def countries
  (->> country-data
       (mapv (comp clojure.string/lower-case first))
       sort
       distinct))

(def capitals
  (->> country-data
       (mapv (comp clojure.string/lower-case second))
       sort))

(defn compare-two? [country capital]
  (let [denominator (float (count (partition 2 (interleave country capital))))
        numerator (count (filter (fn [[a b]] (= a b)) (partition 2 (interleave country capital))))] 
    (< 0.6 (/ numerator denominator))))

(def answer?
  (for [x countries
        y capitals
        :let [t? (compare-two? x y)]
        :when (true? t?)]
   [x y]))
