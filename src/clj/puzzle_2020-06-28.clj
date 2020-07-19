; https://www.npr.org/2020/06/28/884245289/sunday-puzzle-starting-a-new-phrasehttps://www.npr.org/2020/06/28/884245289/sunday-puzzle-starting-a-new-phrase

; Think of a five-letter animal. Remove the middle letter, 
; and two opposites remain. What animal is it?

(def all-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(def fives
  (filter (comp (partial = 5) count) all-words))

(def fours
  (set (filter (comp (partial = 4) count) all-words)))

(def twos
  (->> all-words
       (filter (comp (partial = 2) count))
       set))

(defn meets-criteria? [w wset]
  (let [w1 (subs w 0 2)
        w2 (subs w 3)]
    (and (contains? wset w1)
         (contains? wset w2))))

(defn meets2? [wset w]
  (let [w1 (subs w 0 2)
        w2 (subs w 3)]
    (contains? wset (str w1 w2))))

(defn remove-middle [w]
  (let [w1 (subs w 0 2)
        w2 (subs w 3)]
    (str w1 w2)))

(defn remove-middle2 [w]
  (let [w1 (subs w 0 2)
        w2 (subs w 3)]
    (vector w1 w2)))

(def animals
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "05") last))
       (filter (comp (partial = 5) count first))
       (remove (comp (partial re-find #"\_") first))
       (map first)
       (map clojure.string/lower-case)
       set))

(def solution
  (sort (filter (partial meets2? fours) animals)))

; si no
; ma pa
