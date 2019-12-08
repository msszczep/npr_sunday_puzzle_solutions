; https://www.npr.org/2019/12/08/785832131/sunday-puzzle-bs-and-l-s

; Name a food in two words — a total of 11 letters. Some of these 
; letters appear more than once. The food has seven different letters 
; in its name. You can rearrange these seven letters to identify the 
; form in which this food is typically served. What food is it?

(def all-in-moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       (apply concat)
       (map clojure.string/lower-case)
       set))


(defn two-words? [word]
  (let [f (frequencies word)]
    (= 1 (get f \space))))


(defn eleven-letters-long? [word]
  (let [f (dissoc (frequencies word) \space)]
    (= 11 (apply + (vals f)))))


(defn seven-distinct-letters? [word]
  (let [f (dissoc (frequencies word) \space)]
    (= 7 (count (keys f)))))


(defn get-seven-letters [word]
  (-> word
      frequencies
      (dissoc \space)
      keys
      set))


(defn find-seven-letter-words [words word-to-compare]
  (let [letter-set (get-seven-letters word-to-compare)
        words-to-use (filter (comp (partial = 7) count) words)]
    (filter (comp (partial = letter-set) get-seven-letters) 
            words-to-use)))


(def solution-set
  (->> all-in-moby
       (filter two-words?)
       (filter eleven-letters-long?)
       (filter seven-distinct-letters?)
       (map (juxt identity (partial find-seven-letter-words all-in-moby)))
       (remove (comp empty? second))
       sort))

; (clojure.pprint solution-set)
