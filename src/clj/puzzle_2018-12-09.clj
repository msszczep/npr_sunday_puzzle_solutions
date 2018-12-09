; https://www.npr.org/2018/12/09/674772899/sunday-puzzle-familiar-phrases

; Think of a word that can go _before_ "table" to make a familiar 
; phrase. Move the last letter to the front, and you'll have a word 
; that can go after "table" to make a familiar phrase. What phrases are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(defn move-letter-to-front [word]
  (let [letters (map char word)]
    (apply str (cons (last letters) (butlast letters)))))

 
(def solution
  (->> words
       (map (juxt identity move-letter-to-front))
       (filter (comp words second))
       (map #(str (first %) " table | table " (last %)))))
