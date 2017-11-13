; http://www.npr.org/2017/11/05/561980137/sunday-puzzle-whats-in-style

; Think of the last name of a famous film director. The first two 
; letters and last two letters in order spell a word. And the 
; remaining letters, rearranged, spell a synonym of that word. 
; What film director is it?


 (def words (set (clojure.string/split-lines (slurp "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/ospd3.txt"))))

(->> "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/film_directors_clean.txt"     
     clojure.java.io/reader
     line-seq
     (map (juxt identity (comp clojure.string/lower-case 
                               last 
                               #(clojure.string/split % #" "))))
     (filter (comp (partial < 6) count last))
     (map (juxt first last (comp #(str (subs % 0 2) (subs % (- (count %) 2) (count %))) last)))
     (filter (comp words last))
     (drop 40)
     (take 20)
)

