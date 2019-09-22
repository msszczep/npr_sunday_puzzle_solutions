; https://www.npr.org/2019/09/15/760912776/sunday-puzzle-its-game-day

; Name a world capital in 12 letters. If you have the right one, you 
; can rearrange its letters to name two animals — one in three letters 
; and the other in nine. What capital is it, and what are the animals?

(def twelve-letter-world-capitals
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") second))
       (filter (comp (partial < 12) count))
       ;(filter (partial re-find #"^[A-Za-z]{12}$"))
))


