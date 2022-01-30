; https://www.npr.org/2022/01/30/1076508422/sunday-puzzle-the-category-is

; Think of a familiar two-word phrase meaning "to fight." Change the 
; third letter of each word to get two new words that are opposites of 
; each other. What words are these?

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (filter (partial re-find #"^fight,"))
       (map #(clojure.string/split % #","))
       flatten
       distinct
       (filter (comp (partial = 2) count #(clojure.string/split % #" ")))
       sort
       ))




