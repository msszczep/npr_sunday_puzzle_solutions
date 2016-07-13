;; https://www.facebook.com/deanradcliffe/posts/10154177660167900

;; Paraphrased challenge: What is the longest word [in English] that you
;; can make without repeating any letters?

(->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ni2.txt")
     clojure.string/split-lines
     (filter (comp (partial = #{1})
                   set
                   vals
                   frequencies
                   clojure.string/lower-case))
     (sort-by count)
     reverse
     (take 10)
     )

