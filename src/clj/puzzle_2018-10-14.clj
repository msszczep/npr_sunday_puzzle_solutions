; https://www.npr.org/2018/10/14/657222813/sunday-puzzle-cal-it-like-it-is

; Take the 7-letter last name of a famous woman. Drop the letter E. 
; Add an I and an F. You can rearrange the result to get a word 
; that famously describes this woman. Who's the woman, and what's 
; the word?


(def common-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 8) count))
        (filter (partial re-find #"[iI]"))
        (filter (partial re-find #"[fF]"))
        set))


(def proper-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> %
                  (clojure.string/split #"  " 2)
                  first
                  clojure.string/lower-case))
        (filter (comp (partial = 7) count))
        (filter (partial re-find #"^[a-z]+$"))
        (filter (partial re-find #"[eE]"))
        set))


(def solution-space
 (remove (comp empty? second)
  (for [p proper-words]
    (let [transformed-p (frequencies (str (clojure.string/replace-first p #"e" "") "if"))
          filtered-words (filter #(= (frequencies %) transformed-p) common-words)]
      [p filtered-words]))))
