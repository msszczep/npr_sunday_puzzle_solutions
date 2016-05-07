;; http://www.npr.org/2015/08/16/432386626/a-puzzle-thatll-have-you-floored-in-florida-and-across-the-u-s
;;
;; Take the word EASILY. You can rearrange its letters to spell SAY and LEI.
;; These two words rhyme even though they have no letters in common.

;; What is the longest familiar word you can find that can be anagrammed
;; into two shorter words that rhyme but have no letters in common? The two
;; shorter words must have only one syllable.

;; NB: Stole a bunch from combinatorics.clj

(defn- all-different?
  "Annoyingly, the built-in distinct? doesn't handle 0 args, so we need
to write our own version that considers the empty-list to be distinct"
  [s]
  (if (seq s)
    (apply distinct? s)
    true))

(defn- index-combinations
  [n cnt]
  (lazy-seq
    (let [c (vec (cons nil (for [j (range 1 (inc n))] (+ j cnt (- (inc n)))))),
          iter-comb
          (fn iter-comb [c j]
            (if (> j n) nil
              (let [c (assoc c j (dec (c j)))]
                (if (< (c j) j) [c (inc j)]
                  (loop [c c, j j]
                    (if (= j 1) [c j]
                      (recur (assoc c (dec j) (dec (c j))) (dec j)))))))),
          step
          (fn step [c j]
            (cons (rseq (subvec c 1 (inc n)))
                  (lazy-seq (let [next-step (iter-comb c j)]
                              (when next-step (step (next-step 0) (next-step 1)))))))]
      (step c 1))))

(defn- distribute [m index total distribution already-distributed]
  (loop [distribution distribution
         index index
         already-distributed already-distributed]
    (if (>= index (count m)) nil
      (let [quantity-to-distribute (- total already-distributed)
            mi (m index)]
        (if (<= quantity-to-distribute mi)
          (conj distribution [index quantity-to-distribute total])
          (recur (conj distribution [index mi (+ already-distributed mi)])
                 (inc index)
                 (+ already-distributed mi)))))))

(defn- next-distribution [m total distribution]
  (let [[index this-bucket this-and-to-the-left] (peek distribution)]
    (cond
      (< index (dec (count m)))
      (if (= this-bucket 1)
        (conj (pop distribution) [(inc index) 1 this-and-to-the-left])
        (conj (pop distribution)
              [index (dec this-bucket) (dec this-and-to-the-left)]
              [(inc index) 1 this-and-to-the-left])),
      ; so we have stuff in the last bucket
      (= this-bucket total) nil
      :else
      (loop [distribution (pop distribution)],
        (let
          [[index this-bucket this-and-to-the-left] (peek distribution),
           distribution (if (= this-bucket 1)
                          (pop distribution)
                          (conj (pop distribution)
                                [index (dec this-bucket) (dec this-and-to-the-left)]))],
          (cond
            (<= (- total (dec this-and-to-the-left)) (apply + (subvec m (inc index))))
            (distribute m (inc index) total distribution (dec this-and-to-the-left)),

            (seq distribution) (recur distribution)
            :else nil))))))

(defn- bounded-distributions
  [m t]
  (let [step
        (fn step [distribution]
          (cons distribution
                (lazy-seq (when-let [next-step (next-distribution m t distribution)]
                            (step next-step)))))]
    (step (distribute m 0 t [] 0))))

(defn- multi-comb
  "Handles the case when you want the combinations of a list with duplicate items."
  [l t]
  (let [f (frequencies l),
        v (vec (distinct l)),
        domain (range (count v))
        m (vec (for [i domain] (f (v i))))
        qs (bounded-distributions m t)]
    (for [q qs]
      (apply concat
             (for [[index this-bucket _] q]
               (repeat this-bucket (v index)))))))

(defn combinations
  "All the unique ways of taking t different elements from items"
  [items t]
  (let [v-items (vec (reverse items))]
    (if (zero? t) (list ())
      (let [cnt (count items)]
        (cond (> t cnt) nil
              (= t 1) (for [item (distinct items)] (list item))
              (all-different? items) (if (= t cnt)
                                        (list (seq items))
                                        (map #(map v-items %) (index-combinations t cnt))),
              :else (multi-comb items t)))))
  )

;; The real fun begins here!
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

(def one-syllable-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict.txt")
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (filter #(find-monosyllables (second %)))))

(def rhymed-words-map
  (group-by #(get-nucleus-and-coda (second %)) one-syllable-words))

;; (count rhymed-words-map) -> 1276
;; (take 20 rhymed-words-map)

(defn get-words [v] (map first v))

(def sets-of-words (map #(get-words (val %)) rhymed-words-map))

;; (apply max (map count sets-of-words)) -> 186
;; (filter #(= 186 (count %)) sets-of-words) -> rhymes with "boo"

(def get-word-pairs
  (->> (map #(combinations % 2) sets-of-words)
       (remove nil?)
       flatten
       (partition 2)))

(defn check-if-words-have-shared-letters [words]
  (empty? (clojure.set/intersection (set (butlast (map char (first words))))
                                    (set (butlast (map char (last words)))))))

(def final-pairs
  (filter check-if-words-have-shared-letters get-word-pairs))

;; (count final-pairs) -> 17687

(def all-the-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/xbu.txt")
       clojure.string/split-lines))

;; (count all-the-words) -> 234936

(defn compare-pair-to-word [pair word]
  (let [p-result (-> (apply str pair)
                     (clojure.string/replace #"\d" "")
                     (clojure.string/lower-case)
                     sort)
        w-result (apply str (sort word))]
    (= w-result (apply str p-result))))

;; (compare-pair-to-word '("lei" "say") "easily")

;; at last?!
(remove nil? (for [w all-the-words
      p final-pairs]
  (when (compare-pair-to-word p w) [w p])))
