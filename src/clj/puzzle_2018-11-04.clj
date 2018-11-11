; https://www.npr.org/2018/11/04/663572384/sunday-puzzle-can-you-convert-these-euros

; Think of an article of apparel in eight letters. Drop the last 
; 2 letters. Move what are the now the last 2 letters to the front. 
; You'll get an article of apparel in 6 letters. What is it?


#_(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(def words 
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(-> %
                 (clojure.string/split #"  " 2)
                 first
                 clojure.string/lower-case
                 (clojure.string/replace #"[^a-z]" "")))))

#_(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)))

(def six-letter-words
  (set (filter (comp (partial = 6) count) words)))

(def eight-letter-words
  (set (filter (comp (partial = 8) count) words)))

(defn transform-word [w]
  (let [[a b c d e f g h] (map char w)]
    (str e f a b c d)))

(def final-answer
  (->> eight-letter-words
       (map (juxt identity transform-word))
       (filter (comp six-letter-words second))
       sort))






