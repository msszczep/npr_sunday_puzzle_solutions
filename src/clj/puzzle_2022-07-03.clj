; https://www.npr.org/2022/07/03/1109607639/sunday-puzzle-states-in-rhyme

; Name a well-known fictional character in two words. Remove two 
; letters from the first word in the name. The result is the plural 
; form of the second word. What character is this?

(def fcs
  (->> "resources/fictional_characters_common.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(defn two-words? [s]
  (= 2 (count (clojure.string/split s #" "))))

(defn ends-in-s? [s]
  (->> #" "
       (clojure.string/split s)
       (mapv last)
       (= [\s \s]))
  )

(defn first-ends-in-s? [s]
  (->> #" "
       (clojure.string/split s)
       (mapv last)
       first
       (= \s))
  )

(sort (filter first-ends-in-s? (filter two-words? fcs)))



