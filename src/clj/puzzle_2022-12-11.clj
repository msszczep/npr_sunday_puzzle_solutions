; https://www.npr.org/2022/12/11/1141655117/sunday-puzzle-battle-of-the-board-games

; Many people carry _____ (4-letter word) in a _____ (5-letter word) 
; to make _____ (9-letter word). You can rearrange the letters of the first 
; two words (the 4- and 5-letter ones) to get the last word (the 9-letter one). 
; What words are these?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def nouns-to-use
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "06") last))
       (map first)
       (filter (comp (partial < 2) count))
       (mapv clean-up-word)
       distinct))

(def foods-to-use
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "13") last))
       (map first)
       (filter (comp (partial < 2) count))
       (mapv clean-up-word)
       distinct))

(defn filter-by-length [n]
  (filter (comp (partial = n) count) nouns-to-use))

(def fours (filter-by-length 4))

(def fives (filter-by-length 5))

(def four-foods (filter (comp (partial = 4) count) foods-to-use))

(def nine-foods (filter (comp (partial = 9) count) foods-to-use))

(def fives-swc (filter (comp not #{\a \e \i \o \u} first) fives))

(def nines (set (filter-by-length 9)))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(defn find-solution-by-five [five]
  (for [four fours
        :let [fwords (filter (partial anagram? (str four five)) nines)]
        :when (not (empty? fwords))]
    [four five fwords]))

(defn find-solution-by-four [four]
  (for [five fives
        :let [fwords (filter (partial anagram? (str four five)) nines)]
        :when (not (empty? fwords))]
    [four five fwords]))

(def sol-foods
 (for [four four-foods
       :let [fwords (filter (partial anagram? (str four "spoon")) nine-foods)]
       :when (not (empty? fwords))]
   [four "spoon" fwords]))
