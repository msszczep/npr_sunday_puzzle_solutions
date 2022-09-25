; https://www.npr.org/2022/09/11/1122220815/sunday-puzzle-word-scramble

; Name something, in eight letters, that you might hear at an opera. 
; Drop three of the letters, without changing the order of the remaining 
; five. You'll name something you might see at an opera. What things 
; are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) cmu-words)))

(def fives (filter-word-count 5))
(def eights (filter-word-count 8))

(defn compare-words [five eight]
  (->> eight
       (filter (set five))
       (apply str)
       (= five)))

(def solution
  (for [five fives
        :when (compare-words five eight)]
    [eight five]))

(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(def opera-words
  (->> moby
       (filter (comp not empty? (partial filter #{"opera"}) set))
       flatten
       set))

(filter (comp opera-words first) solution)

