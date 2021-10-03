; https://www.npr.org/2021/10/03/1042727963/sunday-puzzle-categorically-correct

; Write down the name of a country and its largest city, one after the other. 
; Hidden in this string, in consecutive letters, is another country's capital 
; (in six letters)? What is it?

(def country-data
  (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))))

(def six-letter-capitals
  (->> country-data
       (mapv last)
       (filter (comp (partial = 6) count))
       (mapv clojure.string/lower-case)
       sort))

(def countries
  (->> country-data
       (mapv (comp clojure.string/lower-case first))
       sort))

(remove (comp empty? first)
  (let [n 2]
    (for [s six-letter-capitals]
      [(filter #(= (take n s) (take-last n %)) 
                 countries) s])))
