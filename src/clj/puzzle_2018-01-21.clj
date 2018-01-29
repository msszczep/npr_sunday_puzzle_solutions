; https://www.npr.org/2018/01/21/579110492/sunday-puzzle-it-takes-two

; Take the name of a conveyance in seven letters. Drop the middle 
; letter, and the remaining letters can be rearranged to name the 
; place where such a conveyance is often used. What is it?

(def w
  (letfn [(filter-words [words-to-filter n]
            (filter (comp (partial = n) count) words-to-filter))
          (compare-words [seven-letter-word six-letter-word]
            (= (frequencies six-letter-word)
               (frequencies (concat (subs seven-letter-word 0 3) 
                                    (subs seven-letter-word 4)))))]
    (let [words (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/ospd3.txt"
                     slurp
                     clojure.string/split-lines)
          seven-letter-words (filter-words words 7)
          six-letter-words (filter-words words 6)]
      (remove (comp zero? count second)
        (for [s seven-letter-words]
         [s (filter #(compare-words s %) six-letter-words)])))))
