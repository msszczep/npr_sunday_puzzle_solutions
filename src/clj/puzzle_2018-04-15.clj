; https://www.npr.org/2018/04/15/602535575/sunday-puzzle-easy-as-pie

; The letters of SWITZERLAND can be rearranged to spell LIZARD and NEWTS 
; — LIZARD being the singular name of an animal, and NEWTS a plural. 
; Name another country with this same property. That is, name another 
; country whose letters can be rearranged to spell two animals — 
; one singular and one plural. It's a major country. What country is it?

;(require )

(def answer
  (let [countries
          (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))
               (map first)
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " "")))
        scrabble-words
          (->> "resources/ospd3.txt"
               slurp
               clojure.string/split-lines
               set)
        animal-singular
          (->> "resources/roget_clean.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #" => "))
               (filter (comp (partial = "animal 366") first))
               first
               second)
        ]
    (letfn [(get-distinct-letters [w]
              ((comp set keys frequencies) w))
            (get-words-for-country [country]
              (filter #(clojure.set/subset? (get-distinct-letters %) 
                                            (get-distinct-letters country))
                       scrabble-words))]
      (for [country #{"singapore"}
            word1 (get-words-for-country country)
            word2 (get-words-for-country country)
            :when (and (not= word1 word2)
                       (= (frequencies country)
                          (merge-with + (frequencies word1)
                                      (frequencies word2))))]
        [country word1 word2]))))

; japan australia france canada china southkorea russia saudiarabia greatbritain

; snaore 
