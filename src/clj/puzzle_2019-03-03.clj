; https://www.npr.org/2019/03/03/699735287/sunday-puzzle-in-this-game-a-chance-to-claim-vic-tor-y

; Name a popular restaurant chain in two words. Its 
; letters can be rearranged to spell some things to 
; eat and some things to drink. Both are plural words. 
; What things are these, and what's the chain?

(defn get-two-word-phrases [phrase]
  (-> phrase
      (clojure.string/split #" ")
      count
      (= 2)))

(def scrabble-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(defn normalize-term [s]
  (let [letters (set (map char "abcdefghijklmnopqrstuvwxyz"))]
    (->> s
         clojure.string/lower-case
         (map char)
         (filter letters)
         (apply str))))


(defn filter-subanagrams [source]
  (let [n-source (normalize-term source)]
   (->> scrabble-words
        (filter (fn [word]
                  (let [f-word (frequencies word)
                        f-source (merge 
                                  (zipmap "abcdefghijklmnopqrstuvwxyz" (repeat 26 0))
                                  (frequencies n-source))]
                    (every? #(<= (f-word %)
                                 (f-source %))
                            (set word))))))))


(def two-word-restaurant-chains
  (->> "resources/restaurant_chains.txt"
       slurp
       clojure.string/split-lines
       (filter get-two-word-phrases)))

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
  [t items]
  (let [v-items (vec (reverse items))]
    (if (zero? t) (list ())
      (let [cnt (count items)]
        (cond (> t cnt) nil
              (= t 1) (for [item (distinct items)] (list item))
              (all-different? items) (if (= t cnt)
                                        (list (seq items))
                                        (map #(map v-items %) (index-combinations t cnt))),
              :else (multi-comb items t))))))


(defn do-they-fit? [source [w1 w2]]
  (= (frequencies source)
     (frequencies (str w1 w2))))


(defn find-solution [source]
  (let [n (normalize-term source)]
   (->> n
        filter-subanagrams
        (combinations 2)
        (filter (partial do-they-fit? n)))))


(def final-answer
  (->> two-word-restaurant-chains
       (map (juxt identity find-solution))))
