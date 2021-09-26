; https://www.npr.org/2021/09/19/1038562762/sunday-puzzle-rare-letter-swap

; Name something grown in a garden. Change the second letter, and double 
; the third letter, to get an adjective that describes this thing. 
; What is it?

(def all-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial < 3) count))
        set))

(defn third-doubled? [s]
  (let [lets (map char s)]
    (if (< (count s) 3)
      false
      (= (nth lets 2) (nth lets 3)))))

(defn garden-filter [adjective other-word]
  (let [adj-chars (map char adjective)
        word-chars (map char other-word)]
    (and (= (first adj-chars) (first word-chars))
         (not= (nth adj-chars 1) (nth word-chars 1))
         (= (nth adj-chars 2) (nth word-chars 2) (nth word-chars 3))
         (= (drop 3 adjective) (drop 4 other-word)))))

(defn filter-candidate-words [set-to-use word]
  (filter (partial garden-filter word) set-to-use))

(def adjectives
  (->> "resources/wordnet_data_cleaned.adj"
       slurp
       clojure.string/split-lines
       (map #(-> % 
                 (clojure.string/split #" ")
                 (nth 4)))
       (filter (comp (partial < 4) count))
       (filter third-doubled?)
       distinct
       sort))

(def answer
  (filter (comp not empty? last) (mapv (juxt identity (partial filter-candidate-words all-words)) adjectives)))


