; https://www.npr.org/2018/05/27/614535615/sunday-puzzle-yo

; Name part of the human body. Switch the first two letters 
; to get a two-word phrase for something that is worrisome. What is it? 

(def answer
  (let [words
          (->> "resources/ospd3.txt"
               slurp
               clojure.string/split-lines
               set)]
    (letfn [(partition-me [w] (->> (map #(split-at % w) (range 1 (count w)))
                                   (map #(map (partial apply str) %))))
            (switch-first-two-letters [word]
              (str (subs word 1 2)
                   (subs word 0 1)
                   (subs word 2)))
            (both-words-in-words [low]
               (pos? (count (filter #(and (words (first %)) (words (last %))) low))))]
      (->> words
           (map (juxt identity (comp partition-me switch-first-two-letters)))
           (filter (comp both-words-in-words last))
      )
)))
