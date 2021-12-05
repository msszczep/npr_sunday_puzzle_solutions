; https://www.npr.org/2021/12/05/1061517686/sunday-puzzle-its-not-rhyme-thyme

; Think of a word to describe a single animal. Change the third letter to get 
; a word that describes the plural of that animal. Both are nouns, and neither 
; word contains an "s."

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def words-to-use
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "05") last))
       (map first)
       (filter (comp (partial < 2) count))
       (mapv clean-up-word)))

(defn third-letter-same? [w1 w2]
  (let [[a1 b1 c1 & d1] (map char w1)
        [a2 b2 c2 & d2] (map char w2)]
    (and (= a1 a2) (= b1 b2) (not= c1 c2) (= d1 d2))))

(def answer?
  (for [x (sort (distinct words-to-use))
        y (sort (distinct words-to-use))
        :when (and (third-letter-same? x y)
                   (not= x y))]
    [x y]))








