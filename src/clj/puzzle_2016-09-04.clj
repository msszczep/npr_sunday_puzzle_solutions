;; http://www.npr.org/2016/09/04/492557498/the-answer-remains-the-same-whichever-way-you-want-to-look-at-it

;; If you squish the small letters "r" and "n" too closely together,
;; they look like an "m." Think of a common five-letter word with the
;; consecutive letters "r" and "n" that becomes its own opposite if you
;; change them to an "m."

(let [words (->> ; all the words
              (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
              clojure.string/split-lines
              set)]
 (->> words
     (filter (comp (partial = 5) count)) ; get five-letter words...
     (filter (partial re-find #"rn"))    ; that have 'rn'
     (map (juxt identity #(clojure.string/replace % #"rn" "m")))
                                         ; replace "rn" with "m"
     (filter (comp words second))))      ; and check for sets that exist
