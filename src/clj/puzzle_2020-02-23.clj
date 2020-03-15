; https://www.npr.org/2020/02/23/808505912/sunday-puzzle-your-favorite-dessert

; Name a well-known game in 8 letters. Drop the fifth letter. Move the first 
; letter into the vacated spot ... and you'll spell, in order, part of the human 
; body. What game is it, and what's the body part?

(def games
  (->> "resources/board_games.txt"
       slurp
       read-string
       (map #(clojure.string/replace % #"[^A-Za-z]" ""))
       (filter (comp (partial = 8) count))
       (map clojure.string/lower-case)))

(def body-parts
  (->> "resources/body_parts.txt"
       slurp
       (clojure.string/split-lines)
       (map #(clojure.string/replace % #"[^A-Za-z]" ""))
       (filter (comp (partial = 7) count))))


(defn transform [s]
  (let [[a b c d e f g h] (map char s)]
    (str b c d a f g h)))


(def result 
  (map (juxt identity transform) games))








