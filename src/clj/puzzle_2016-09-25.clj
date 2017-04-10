;; Take the words DOES, TOES, and SHOES.  They all end in the same three letters
;; but none of them rhyme.  What words starting with F, S and G have the same
;; property?  The F and S words are four letters long, and the G word is five letters.
;; They all end in the same three letters.

(let [vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
              "AY" "IY" "ER" "EY" "AW" "OY" "UW"}
      terms
      (->> (slurp (clojure.java.io/resource "cmudict-0.7b.txt"))
           clojure.string/split-lines
           (map #(clojure.string/replace-first % #" " ""))
           (map #(clojure.string/split % #" " 2))
           (map (juxt first #(clojure.string/replace (second %) #"\d" "")))
           (map (juxt first #(clojure.string/split (second %) #" "))))]
  (letfn [(filter-words [num-letters first-letter]
            (->> terms
                 (filter (comp (partial = num-letters) count first))
                 (filter (comp (partial re-find
                                        (re-pattern (str "^" first-letter)))
                               first))
                 (map (juxt first #(drop-while
                                     (comp not
                                       (partial contains? vowels))
                                 (second %))))
                 (map (juxt first (comp (partial apply str) second)))))]
      (->> (concat (filter-words 4 "F") (filter-words 4 "S") (filter-words 5 "G"))
           (group-by #(subs (first %) (if (= 4 (count (first %))) 1 2)))
           (filter (comp (partial < 2) count second))
           (filter (comp (partial = 3) count set (comp (partial map second) second)))
           (map (comp (partial map first) second))
           (remove (comp (partial not= 3) count set (partial map first)))
           (filter (comp (partial = 3) count)))))
