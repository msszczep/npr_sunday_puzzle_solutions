; https://www.npr.org/2019/05/19/724697693/sunday-puzzle-play-the-long-vowel-game

; Name a profession in 13 letters that is associated with a particular 5-letter 
; country. The letters of that country appear in left-to-right order, although 
; not consecutively, in that profession's name. What is it?
; Hint: The profession is a single word.

(def five-letter-countries
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") first))
       (filter (comp (partial = 5) count))
       (map clojure.string/upper-case)
       set))

(def thirteen-letter-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^A-Z]" ""))
        (filter (comp (partial = 13) count))
        set))

(defn check-frequencies [thirteen five]
  (let [freqs-thirteen (frequencies thirteen)
        freqs-five (frequencies five)
        letters-to-use (keys freqs-five)]
    (every? (fn [l] (and (not (nil? (get freqs-five l)))
                         (>= (get freqs-thirteen l -1) 
                             (get freqs-five l -1)))) 
            letters-to-use)))

#_(defn check-order [thirteen five]
  (every? (fn [l] (and (check-frequencies thirteen five)
                       (>=))
  ) five))

(def solution
  (->> (for [t thirteen-letter-words
             f five-letter-countries
             :when (check-frequencies t f)]
         [t f])))
