; https://www.npr.org/2023/03/12/1162867188/sunday-puzzle-around-the-world-in-nine-words

; Name two countries that have consonyms that are nationalities 
; of other countries. In each case, the consonants in the name 
; of the country are the same consonants in the same order as 
; those in the nationality of another country. No extra 
; consonants can appear in either name. The letter Y isn't used.

(def country-data
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(def countries
  (->> country-data
       (mapv (comp clojure.string/upper-case first))
       sort
       distinct))

(defn filter-out-consonants [w]
  (->> w
       (map char)
       (remove #{\A \E \I \O \U})))

(remove (comp (partial > 2) count val) (group-by filter-out-consonants countries))
