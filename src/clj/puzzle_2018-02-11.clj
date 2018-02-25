; https://www.npr.org/2018/02/11/584000987/sunday-puzzle-put-your-back-into-it

; Name part of the human body in six letters. Add an R and rearrange the 
; result to name a part of the body in seven letters. What is it?

(def answer
  (let [words 
         (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/ospd3.txt"
              slurp
              clojure.string/split-lines)
;        six-letter-words (filter (comp (partial = 6) count) words)
        seven-letter-words (filter (comp (partial = 7) count) words)
        sevens-with-rs (filter (partial re-find #"r") seven-letter-words)]
    (for [six ["kisser" "larynx" "muzzle" "noggin" "noodle" "scruff" 
               "septum" "temple" "throat" "tongue" "tonsil" "cornea" "eyelid"]
          seven sevens-with-rs
          :when (= (frequencies seven)
                   (frequencies (str six \r)))]
         [six seven]))) 
