;; http://www.npr.org/2017/03/19/520595633/sunday-puzzle-youll-need-to-unscramble-the-opposition

;; Think of a familiar phrase in the form "I ___ you," in which a four-letter
;; word goes in the blank. Rearrange those letters and you'll get another
;; familiar phrase in the form "I ___ you." Both phrases get more than half a
;; million hits in a Google search. What phrases are these?

(->> (slurp (clojure.java.io/resource "ospd3.txt"))
     clojure.string/split-lines              ;; split lines
     (filter (comp (partial = 4) count))     ;; get list of 4-letter words
     (group-by frequencies)
     (filter (comp (partial < 1) count val))
     vals
     sort
;     count
     )

; dare, read
