;; http://www.npr.org/2015/12/13/459493157/when-fbi-gets-involved-in-puzzles-clues-are-just-the-half-of-it

;; Name a well-known character of TV, movies and comics. Two words. Replace the 8th, 9th, and 10th letters
;; with an S.  Then rearrange the result to name a well-known actor who played this character on film.
;; First and last names. Who is it?

(def fict-chars
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/fc.txt")
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       (remove #(re-find #":" %))
       (filter #(re-find #"^[A-Z][a-z]+[A-Z][a-z]+$" %))
       (filter #(< 9 (count %)))
       ))


(def fc
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/fc_common.txt")
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       (filter #(= 1 (count (re-seq #"\s" %))))
       (filter #(< 10 (count %)))
       ))


(sort fc)
