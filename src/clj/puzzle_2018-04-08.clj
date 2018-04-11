; https://www.npr.org/2018/04/08/600616562/sunday-puzzle-last-weeks-puzzle-catches-some-listeners-off-guard

;  Name part of the human body. Insert a speech hesitation, 
; and you'll name a country. What country is it?

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
               set)]
    (letfn [(get-nums [s] 
      (for [x (range 1 (dec (count s)))
            y (range x (dec (count s)))
            :when (not= x y)]
        [x y]))]
     (remove nil?
      (for [country countries]
        (let [country-substrings (map (fn [[f s]] 
                                        (str (subs country 0 f)
                                             (subs country s (count country))))                                      
                                      (get-nums country))]
          (if (some scrabble-words country-substrings) 
            [country (filter scrabble-words country-substrings)])))))))

