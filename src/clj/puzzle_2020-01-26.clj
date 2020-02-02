

; Write down the letter C.  Beneath that write ENT. And beneath that write a
; G.  What profession do these letters represent?  Hint: It's a two word phrase -
; ten letters in the first word, five letters in the second.


(def words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #" ") first))
       (map #(clojure.string/replace % #"[^A-Z]" ""))
       ))

(def tens (filter (comp (partial = 10) count) words))

(def fives (filter (comp (partial = 5) count) words))




