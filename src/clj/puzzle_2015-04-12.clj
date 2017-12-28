;http://www.npr.org/2015/04/12/399070578/be-attentive-and-youll-find-tv-in-these-words
;
;Think of a job, in 8 letters, that names someone who might work with actors. Change
;one letter in this to the following letter of the alphabet to name another person who
;works with actors. What jobs are these?


(def eight-letter-words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (filter #(= 8 (count %)))))

; now find all those words that:
;  differ from another word by a single letter
;  that letter is in the same position
;  that letter is adjacent in the alphabet

(defn get-str-diff-count [w]
  (->> (partition 2 (interleave (map char (first w)) (map char (last w))))
       (remove #(= (first %) (last %)))
       count))


(def eight-pairs (partition 2 1 eight-letter-words))

(count
  (map first (filter #(= 1 (val %)) (zipmap eight-pairs (map get-str-diff-count eight-pairs)))
     ))
