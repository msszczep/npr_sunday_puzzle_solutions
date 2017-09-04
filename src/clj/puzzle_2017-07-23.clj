;; 

;; What common three-word expression (14 letters in all) has only 
;; N and G as consonants, and is all vowels otherwise?

(def w 
  (->> "ospd3.txt"
       clojure.java.io/resource
       clojure.java.io/reader
       line-seq
       (filter (partial re-find #"^[aeioung]+$"))))

; going going gone

