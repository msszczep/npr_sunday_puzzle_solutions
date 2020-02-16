; https://www.npr.org/2020/02/16/806329060/sunday-puzzle-double-l-phrases

; What familiar 10-letter word contains a silent B, E, and O — 
; not necessarily in that order. And those three letters don't 
; have to be consecutive in the word.

(def candidate-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"\s" 2))
       flatten
       (partition 2)
       (map (fn [[a b]] [(clojure.string/replace a #"[^A-Z]" "") b]))
       (filter (comp (partial = 10) count first))
       (filter (comp (partial re-find #"[bB]") first))
       (filter (comp (partial re-find #"[eE]") first))
       (filter (comp (partial re-find #"[oO]") first))
       (remove (comp (partial re-find #" B") second))
       (map first)
       distinct))









