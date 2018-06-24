; https://www.npr.org/2018/06/17/620280960/sunday-puzzle

; Think of a familiar hyphenated 7-letter word. The first 
; 4 letters name a prominent American company, and the last 
; 4 letters name a different prominent American company. 
; What word is it?

(def words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
;        (filter (partial re-matches #".*FORD.*"))
        (filter (comp (partial = 7) count (partial re-matches #".*VISA.*")))
;        (filter (comp (partial = 4) count (partial re-matches #"^D.*")))
))  


