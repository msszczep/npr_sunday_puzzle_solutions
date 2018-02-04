;; http://www.npr.org/2015/12/06/458661972/transform-words-with-an-additional-letter-in-this-weeks-puzzle

;; Name a state capital. Drop one of its letters. The remaining letters can be rearranged to
;; name of another major city in the United States. What is it? There are two different answers,
;; and you should find both of them.

(def usa-cities
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/usa_cities_cleaned.txt")
       clojure.string/split-lines
       (map clojure.string/lower-case)
       (map #(clojure.string/replace % #"\s" ""))))

(take 20 usa-cities)

(def cap-cities
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cap_cities_cleaned.txt")
       clojure.string/split-lines
       (map clojure.string/lower-case)
       (map #(clojure.string/replace % #"\s" ""))))

;(take 20 usa-cities)

(take 20 cap-cities)

;; Take one

(defn get-freqs-set [w]
  (->> (map char w)
       frequencies
       set))

(set (frequencies (map char "montgomery")))

(defn check-str-diff-count [words]
  (->> (clojure.set/difference (get-freqs-set (first words))
                               (get-freqs-set (last words)))
       count
       (= 1)))


(filter #(check-str-diff-count %)
  (for [c cap-cities
        u usa-cities
        :when (= (dec (count c)) (count u))]
    [c u]))

;; Take two

(clojure.set/difference (get-freqs-set "banaba") (get-freqs-set "banana") )

;; LOAD ME -> clojure.data/diff

()

(check-str-diff-count ["banana" "banara"])

(defn compare-words [words]
  (letfn [(f [w] (frequencies (map char w)))]
    (filter #(pos? (val %))
            (merge-with -
                        (f (first words))
                        (f (last words))))))


(filter #(= 1 (count (compare-words %)))
        (for [c cap-cities
              u usa-cities
              :when (= (dec (count c)) (count u))]
          [c u]))

