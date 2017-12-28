;; https://www.npr.org/2017/12/03/567605941/sunday-puzzle-the-reverse-is-also-true

;; Take the singular and plural forms of a particular noun. Remove the first two 
;; letters of the singular form and you'll name a country. Remove one letter 
;; from inside the plural form to name another country. What words and countries are 
;; these?

(defn levenshtein [w1 w2]
  (letfn [(cell-value [same-char? prev-row cur-row col-idx]
            (min (inc (nth prev-row col-idx))
                 (inc (last cur-row))
                 (+ (nth prev-row (dec col-idx)) (if same-char?
                                                   0
                                                   1))))]
    (loop [row-idx  1
           max-rows (inc (count w2))
           prev-row (range (inc (count w1)))]
      (if (= row-idx max-rows)
        (last prev-row)
        (let [ch2           (nth w2 (dec row-idx))
              next-prev-row (reduce (fn [cur-row i]
                                      (let [same-char? (= (nth w1 (dec i)) ch2)]
                                        (conj cur-row (cell-value same-char?
                                                                  prev-row
                                                                  cur-row
                                                                  i))))
                                    [row-idx] (range 1 (count prev-row)))]
          (recur (inc row-idx) max-rows next-prev-row))))))


(def countries
   (->> "/Users/mitchells/Scripts/npr_sunday_puzzle_solutions/resources/world-capitals.txt"
        slurp
        clojure.string/split-lines
        (map #(clojure.string/split % #","))
        (map first)
        (map clojure.string/lower-case)
        (map #(clojure.string/replace % #" " ""))
        ))


(def answer (for [x countries
       y countries
       :when (and (not= x y)
                  (< (levenshtein x y) 5))]
       #{x y}))
