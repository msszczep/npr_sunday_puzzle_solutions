; https://www.npr.org/2018/02/04/582750978/sunday-puzzle-stuck-in-the-middle

; In English, a short "u" sound is usually spelled with a "u," as in "fun" 
; and "luck." Occasionally it's spelled with an "o," as in "come" and "love." 
; Can you name two everyday one-syllable words in which a short "u" sound is 
; spelled with an "a"?


(->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/cmudict-0.7b.txt"
     slurp
     clojure.string/split-lines
     (map #(clojure.string/split % #"  "))

     (filter (comp #(re-find #" AH1 " %) second)) 
     ; find the short U sound

     (filter (comp (partial = 1) count #(re-seq #"\d" %) second)) 
     ; find one-syllable words
     
     (filter (comp #(re-find #"A" %) first))) 
     ; find words spelled with A


