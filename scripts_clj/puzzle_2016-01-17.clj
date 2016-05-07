;; http://www.npr.org/2016/01/17/463333521/it-may-be-topsy-turvy-but-this-puzzle-still-invites-categorization

;; vegetable -> lettuce
;; U.S. State -> Texas
;; book of the Bible -> Leviticus

;; Think of a category in three letters in which the last two letters are the first
;; two letters of something in that category. And the thing in the category has seven
;; letters. Both names are common, uncapitalized words. What are they?

;; do it in point free style!

(def all-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines))


(take 30 all-words)

(def three-letter-words
  (filter #(= 3 (count %)) all-words))


(def seven-letter-words
  (filter #(= 7 (count %)) all-words))


(for [tlw three-letter-words]
  [tlw (filter #(= (subs tlw 1 3) (subs % 0 2)) seven-letter-words)]
  )

