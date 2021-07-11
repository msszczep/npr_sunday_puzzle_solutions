; https://www.npr.org/2021/07/11/1014959492/sunday-puzzle-two-consonants

; Think of a country. Embedded in consecutive letters is a well-known 
; brand name. The first, second, eighth and ninth letters of the country, 
; in order, spell a former competitor of that brand. Name the country 
; and the brands.

(def countries 
  (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))
               (map first)
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " ""))
               (filter (comp (partial < 8) count))))

(defn sw [s]
  (let [[a b _ _ _ _ _ c d _] (map char s)]
    (str a b c d)))

(def answer?
  (mapv (juxt identity sw) countries))


