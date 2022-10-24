; https://www.npr.org/2022/10/16/1129314851/sunday-puzzle-words-of-a-feather

; Think of a pair of two-syllable words that are pronounced the same, except 
; one is accented on the first syllable while the other is accented on the 
; second. The word that's accented on the first syllable is associated with 
; confrontation, while the word that's accented on the second syllable is 
; associated with cooperation. What words are these?


(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt (comp clean-up-word first) second))
       distinct
       set))

(defn two-syllables? [entry]
  (= 2 (count (re-seq #"\d" (second entry)))))

(defn return-without-stress [entry]
  (vector (first entry) (second entry) (clojure.string/replace (second entry) #"\d" "")))

(def final-answer
  (mapv (comp (partial map first) second) (filter (comp (partial < 1) count second) (group-by second (mapv return-without-stress (filter two-syllables? cmu-words)))))

  (->> cmu-words
       (filter two-syllables?)
       (mapv return-without-stress)
       (group-by second)
       (filter (comp (partial < 1) count second))
       count))

(filter (comp (partial < 1) count distinct (partial mapv second) second) (filter (comp (partial < 1) count second) (group-by last (mapv return-without-stress (filter two-syllables? cmu-words)))))

(take 100 (mapv (comp (partial mapv first) second) (sort-by first (filter (comp (partial < 1) count distinct (partial mapv second) second) (filter (comp (partial < 1) count second) (group-by last (mapv return-without-stress (filter two-syllables? cmu-words))))))))
