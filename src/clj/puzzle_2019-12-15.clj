; https://www.npr.org/2019/12/15/788095459/sunday-puzzle-the-odd-word-out

; Write down eight different letters of the alphabet. Add an apostrophe. 
; Then write the same eight letters in a different order. With proper 
; spacing, you now have a four-word phrase meaning "took a risk." What is it?

(def all-in-moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))


(defn get-risky-wordsets [wordset]
  (->> wordset
       (map clojure.string/lower-case)
       (some (partial re-find #"risk"))))


(defn multiple-words? [word]
  (let [f (frequencies word)]
    (< 1 (get f \space 0))))


(defn eight-letters-long? [word]
  (let [f (dissoc (frequencies word) \space)]
    (>= 8 (apply + (vals f)))))


(defn distinct-letters? [word]
  (let [f (dissoc (frequencies word) \space)]
    (= #{1} (set (vals f)))))


(def solution-set
  (->> all-in-moby
       (filter get-risky-wordsets)
       (apply concat)
       (filter multiple-words?)
       (filter eight-letters-long?)
       (filter distinct-letters?)
       distinct
       sort))


