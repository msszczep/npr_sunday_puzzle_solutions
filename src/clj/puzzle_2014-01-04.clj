;;http://www.npr.org/2014/01/05/259721680/two-times-harder

;; Name something in five letters that's generally pleasant,
;; it's a nice thing to have. Add the letters A and Y, and rearrange
;; the result, keeping the A and Y together as a pair. You'll get the
;; seven-letter word that names an unpleasant version of the
;; five-letter thing. What is it?


(letfn [(get-n-length-words [length]
         (->> (slurp (clojure.java.io/resource "ospd3.txt"))
              clojure.string/split-lines
              (filter (comp (partial = length) count))))
        (check-anagrams [five-letter-word
                         seven-letter-word]
          (= (frequencies (str five-letter-word "ay"))
             (frequencies seven-letter-word)))]
    (let [five-letter-words (get-n-length-words 5)
          seven-letter-words
          (->> (get-n-length-words 7)
               (filter (partial re-find (re-pattern "ay"))))
               ]
      (for [fiver five-letter-words
            sevener seven-letter-words
           :when (check-anagrams fiver sevener)]
        [fiver sevener]))
  )

