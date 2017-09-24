; http://www.npr.org/2017/09/03/548219538/sunday-puzzle-holy-moly-it-s-another-familiar-phrase

; Rearrange the 15 letters of COOL HIT FARE IN L.A. to name 
; a famous song that's appropriate to the given phrase.

(def coolhitfareinla (frequencies "coolhitfareinla"))

(->> "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/enwiki-20131001-all-titles.txt"
     clojure.java.io/reader
     line-seq
     (filter (partial re-find #"^[A-Za-z_]+$"))
     (map #(clojure.string/replace % #"_" ""))
     (map clojure.string/lower-case)
     (filter #(= (frequencies %) coolhitfareinla)))
