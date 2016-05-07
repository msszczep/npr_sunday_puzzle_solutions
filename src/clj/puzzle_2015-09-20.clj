;; http://www.npr.org/2015/09/20/441795832/all-your-favorite-late-night-hosts-with-a-special-guess
;;
;; Take the words FORETOLD and FOURFOLD. They start with homophones, FORE and FOUR,
;; and they end with rhymes, TOLD and FOLD. The challenge is to find two common nine-letter
;; compound words that have the same property. Specifically, the two homophones are each five
;; letters long, and the rhymes have four letters each. What words are these?

(def vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
              "AY" "IY" "ER" "EY" "AW" "OY" "UW"})

(defn wipe-and-split [s]
  (clojure.string/split (clojure.string/replace s #"\d" "") #" "))

(defn find-monosyllables [syllables]
  (->> (wipe-and-split syllables)
       (filter vowels)
       count
       (= 1)))

(defn get-nucleus-and-coda [syllables]
  (->> (wipe-and-split syllables)
       (drop-while #(not (contains? vowels %)))
       (apply str)))

(def four-letter-one-syllable-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (filter #(find-monosyllables (second %)))
       (filter #(= 4 (count (first %))))))

(def rhymed-words-map
  (group-by #(get-nucleus-and-coda (second %)) four-letter-one-syllable-words))
;; (count rhymed-words-map) -> 522

(def list-of-four-letter-pieces
  (->> (map #(map first (second %)) rhymed-words-map)
       (filter #(< 1 (count %)))))
;; (count (flatten list-of-four-letter-pieces)) -> 4293


(def five-letter-homophones
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (filter #(= 5 (count (first %))))
       (group-by #(second %))))
;; (count five-letter-homophones) -> 13050

(def homophones5-non-singletons
  (filter #(< 1 (count (second %))) five-letter-homophones))
;; (count homophones5-non-singletons) -> 1520

(def list-of-five-letter-pieces
  (map #(map first (second %)) homophones5-non-singletons))

;; (count (flatten list-of-five-letter-pieces)) -> 3380

(take 5 list-of-four-letter-pieces)

(take 5 list-of-five-letter-pieces)

(def set-of-nine-letter-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
       clojure.string/split-lines
       (map #(clojure.string/split % #" " 2))
       (map first)
       (filter #(= 9 (count %)))
       (remove #(re-find #"\d+" %))
       (remove #(re-find #"\'" %))
       (remove #(re-find #"\-" %))
       (remove #(re-find #"\_" %))
       set))

set-of-nine-letter-words

(def final-answer
  (remove nil?
          (for [five-piece (flatten list-of-five-letter-pieces)
                four-piece (flatten list-of-four-letter-pieces)]
            (when (contains? set-of-nine-letter-words
                             (str five-piece four-piece))
              (str five-piece four-piece)))))

(sort final-answer)

;(for [five-mother-piece (list-of-five-letter-pieces)
;      four-mother-piece (list-of-four-letter-pieces)]
;            (when (contains? set-of-nine-letter-words
;                             (str five-piece four-piece))
;              (str five-piece four-piece)))
