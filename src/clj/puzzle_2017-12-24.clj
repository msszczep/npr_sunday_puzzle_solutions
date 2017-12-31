; https://www.npr.org/2017/12/24/572833897/sunday-puzzle-initially-you-might-be-right-but-its-time-to-switch-it-up

; The name of what well-known U.S. city, in 10 letters, 
; contains only three different letters of the alphabet?


(->> "/Users/mitchells/Scripts/npr_sunday_puzzle_solutions/resources/us_cities_big_list.txt"     
     clojure.java.io/reader
     line-seq
     (map clojure.string/lower-case)
     (map #(clojure.string/replace % #" " ""))
     (filter (comp (partial = 10) count))
     (filter (comp (partial = 3) count frequencies)))

