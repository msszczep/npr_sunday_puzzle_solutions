;; 

; city in the US 24000 people
; change last letter in the name of the state
; read city and state name in order
; result: palindrome

(->> "us-states.txt"
     clojure.java.io/resource
     clojure.java.io/reader
     line-seq
     (map #(-> %
              (clojure.string/replace #" " "")
              clojure.string/lower-case
              ((juxt (comp (partial apply str) reverse) identity)))))



