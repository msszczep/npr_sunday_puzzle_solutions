; Name something in three syllables that an auto mechanic might have.
; Move the second and third syllables to the front. The result, with
; some respacing, will name a group of auto mechanics. What is it?
;
; http://www.npr.org/2015/07/26/426205052/hustle-puzzle-rustlers-this-weeks-a-toughie

(def three-syllable-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
       (clojure.string/split-lines)
       (map #(clojure.string/split % #"\s" 2))
       flatten
       (partition 2)
       (map (fn [[a b]] [a (count (re-seq
                         #"AA|AE|AH|AO|AW|AY|EH|ER|EY|IH|IY|OW|OY|UH|UW"
                         b))]))
       (filter #(= 3 (second %)))
       (map first)
       distinct
       (remove #(re-seq #"'S$" %))
       (remove #(re-seq #"ED$" %))
       ))

(count three-syllable-words)

