; https://www.npr.org/2019/11/03/775599371/sunday-puzzle-cocoa

; The letters C + D together sound like the word "seedy." And 
; the letters I + V together sound like "ivy." Take the 18 
; letters in the phrase END BACKSTAGE TV QUIZ. Rearrange them 
; into pairs, using each letter exactly once, to make nine common, 
; uncapitalized words phonetically. Can you do it?


(def sounds
  {:N #"EH\d N"
   :E #"IY\d"
   :D #"D IY\d"
   :B #"B IY\d"
   :A #"EY\d"
   :C #"S IY\d"
   :K #"K EY\d"
   :S #"EH\d S"
   :T #"T IY\d"
   :G #"JH IY\d"
   :V #"V IY\d"
   :Q #"K Y UW\d"
   :U #"Y UW\d"
   :I #"AY\d"
   :Z #"Z IY\d"})


(def words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"  "))))


(def solution
  (for [l1 (sort (keys sounds))
        l2 (sort (keys sounds))
        :let [s (re-pattern (str "^" (l1 sounds) " " (l2 sounds) "$"))
              r (filter #(re-find s (second %)) words)]
        :when (and (not= l1 l2)
                   (not (empty? r)))]
    [l1 l2 r]))


