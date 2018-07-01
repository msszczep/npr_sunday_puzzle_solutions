; https://www.npr.org/2018/07/01/625079060/sunday-puzzle-the-other-a-i

; Name a woman's title. Drop the first and last letters and 
; read the result backward to get another woman's title. 
; Both titles are common English-language spellings. What titles are these?

(def all-words
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))


(defn transform-word [word]
  (->> word
       count
       dec
       (subs word 1)
       reverse
       (apply str)))

(def solutions 
  (->> all-words
       (map (juxt identity transform-word))
       (filter (comp all-words last))
       (filter (comp (partial < 3) count last))))


(filter (comp (partial re-find #"ess$") first) solutions)
