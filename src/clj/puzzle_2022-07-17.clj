; https://www.npr.org/2022/07/17/1111886497/sunday-puzzle-know-the-middle-to-solve-the-riddle

; Name a food item in seven letters. Move the first letter to the fifth position 
; and you'll get two words that are synonyms. What are they?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(def twos (set (filter (comp (partial = 2) count) words)))
(def threes (set (filter (comp (partial = 3) count) words)))
(def fours (set(filter (comp (partial = 4) count) words)))
(def fives (set(filter (comp (partial = 5) count) words)))
(def sevens (set (filter (comp (partial = 7) count) words)))

(defn move-letters [word]
  (let [[a b c d e & h] (map char word)]
    (apply str b c d e a h)))
 
(defn check-threes-and-fours [word]
  (let [[a b c d e f g] (map char word)
        a1 (str a b c)
        a2 (str d e f g)
        b1 (str a b c d)
        b2 (str e f g)
        c1 (str a b)
        c2 (str c d e f g)
        d1 (str a b c d e)
        d2 (str f g)]
    (or (and (contains? threes a1)
             (contains? fours a2))
        (and (contains? fours b1)
             (contains? threes b2))
        (and (contains? twos c1)
             (contains? fives c2))
        (and (contains? fives d1)
             (contains? twos d2)))))

(def s2 
  (filter (comp check-threes-and-fours move-letters) sevens))
