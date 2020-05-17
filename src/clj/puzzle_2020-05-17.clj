; https://www.npr.org/2020/05/17/857554125/sunday-puzzle-a-game-of-consonants

; Name a Cabinet department — as in "Department of ___." Rearrange 
; the letters of what goes in the blank to get the brand name of a 
; product you might find at a drugstore or supermarket. What is it?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Z]" "")))

(def cabinet
  (->> "resources/cabinet.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/replace % #" " ""))))

(def words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"  " 2))
       (map (comp clean-up-word first))))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(def answer
  (for [c cabinet] 
   (filter (partial anagram? c) words)))
