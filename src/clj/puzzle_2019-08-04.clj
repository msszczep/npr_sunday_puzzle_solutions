; https://www.npr.org/2019/08/04/748002198/sunday-puzzle-great-crate

; Think of a two-letter and a five-letter word that are synonyms. 
; The two-letter word and the last syllable of the five-letter word 
; sound like new words that are antonyms. What words are these?

(def synsets
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(defn two-and-five? [result-set]
  (= 2 (count (frequencies (map count result-set)))))

(defn filter-two-or-five [word-set]
  (filter #(contains? #{2, 5} (count %)) word-set))

(defn filter-negative [word-set]
  (filter #(contains? (set %) "negative") word-set))

(def answer
  (filter-two-or-five (filter-negative synsets)))



