; https://www.npr.org/2023/03/19/1164033616/sunday-puzzle-back-to-the-bs

; Name two well-known commercial products in five letters whose names 
; are anagrams of each other. One product is something you'd probably 
; see in your bathroom. The second is more likely to be in your refrigerator. 
; What products are these?

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
       (filter (comp (partial = 5) count))
       set))

(def ospd-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 5) count))
        set))

(def proper-words
  (clojure.set/difference cmu-words ospd-words))

(def final-answer 
  (->> proper-words
       (group-by frequencies)
       (filter (comp (partial < 1) count val))))




