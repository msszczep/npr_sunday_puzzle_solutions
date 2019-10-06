; https://www.npr.org/2019/09/29/764814026/sunday-puzzle-ds-and-n-s

; Think of a word that has five vowels — two E's, an I, O, and U. 
; Curiously, every vowel except the "I" is pronounced like a short "I." 
; And the "I" in the word is not pronounced at all. What word is it?

(defn word-with-five-vowels [word]
  (let [f (frequencies word)]
    (and (= 2 (get f \E))
         (= 1 (get f \I))
         (= 1 (get f \O))
         (= 1 (get f \U))
         (nil? (get f \A)))))


(def words-to-use
  (let [letters (set "ABCDEFGHIJKLMNOPQRSTUVWXYZ")]
    (->> "resources/cmudict-0.7b.txt"
         slurp
         clojure.string/split-lines
         (map #(clojure.string/split % #"  "))
         (filter (comp (partial every? letters) first))
         (filter (comp word-with-five-vowels first))
         (filter (comp not nil? (partial re-find #"IH") second))
         (map first)
)))
