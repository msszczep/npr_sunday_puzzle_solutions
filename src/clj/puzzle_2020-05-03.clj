; 

; Think of two common phrases in the form "_ and _" in which
; the blanks stand for four-letter words.  All f our words in 
; those two phrases have different first letters, but the last
; three letters in the words are the same.  What are the phrases?

(def words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 4) count))))

(def filtered
  (->> words
       (group-by #(subs % 1))
       (filter (fn [[k v]] (->> v count (< 3))))
       sort))
