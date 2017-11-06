; http://www.npr.org/2017/09/24/553147004/sunday-puzzle-what-s-in-a-name

; Think of a familiar 6-letter boy's name starting with a vowel. Change 
; the first letter to a consonant to get another familiar boy's name. 
; Then change the first letter to another consonant to get another 
; familiar boy's name. What names are these?

(->> "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/male_first_names_lower.txt"
     clojure.java.io/reader
     line-seq
     (group-by #(subs % 1)) ; group by last five letters
     (filter (comp (partial < 2) count val)) ; get sets of at least three names
     (filter (comp (partial = 5) count key)) ; get six-letter names
     )
