; https://www.npr.org/2022/11/13/1135781599/sunday-puzzle-great-words-think-alike
; Think of two well-known companies with two-syllable names starting 
; with J and D, respectively and whose names rhyme. One of these 
; companies was founded in the last 10 years. What companies are these?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt (comp clean-up-word first) second))
       distinct
       set))

(defn filter-by-first-letter [letter]
  (filter (comp (partial = letter) ffirst) cmu-words))

(def js (filter-by-first-letter \j))

(def ds (filter-by-first-letter \d))

(def all-entries (concat js ds))

(defn get-end-stuff [entry]
  (last (#(clojure.string/split % #" " 2) (last entry))))

(defn wipe-and-split [s]
  (clojure.string/split (clojure.string/replace s #"\d" "") #" "))

(defn find-monosyllables [syllables]
  (->> (wipe-and-split syllables)
       (filter vowels)
       count
       (= 1)))

(def group-by-entries
  (filter (comp (partial < 1) count second) (group-by get-end-stuff all-entries)))

(filter (comp (partial = 2) count set (partial map ffirst) second) group-by-entries)

(defn two-syllables? [entry]
  (= 2 (count (re-seq #"\d" (second entry)))))

(defn return-without-stress [entry]
  (vector (first entry) (second entry) (clojure.string/replace (second entry) #"\d" "")))


