;;

;; two things found in a kitchen, g, k ->
;; rearrange letters one in a kitchen f, elsewhere in house k

; [["griddle" "knife"] ["fridge" "kindle"]]

(letfn [(get-words-starting-with [letter]
         (->> (slurp (clojure.java.io/resource "ospd3.txt"))
              clojure.string/split-lines
              (filter (partial re-find (re-pattern (str "^" letter))))))]
  (let [f-words (get-words-starting-with "f")
        g-words (get-words-starting-with "g")
        k-words (get-words-starting-with "k")]
    (for [g g-words
          k1 ["knife"]
          f ["fridge"]
          k2 k-words
          :when (and (= (frequencies (str g k1))
                        (frequencies (str f k2)))
                     (not= k1 k2))]
      [[g k1] [f k2]])))

; g -> 3229
; k -> 1226
; f -> 3651
