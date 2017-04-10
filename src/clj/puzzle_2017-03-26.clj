;; http://www.npr.org/2017/03/26/521446835/sunday-puzzle-keep-the-bookend-letters-from-these-words-to-make-new-ones

;; Name two things found in a kitchen — one starting with G, the other starting with K.
;; If you have the right ones, you can rearrange the letters to name two other things,
;; one of them found in the kitchen starting with F, the other one probably found elsewhere
;; in the house starting with K. What things are these?

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

; (* 3229 1226 3651)
