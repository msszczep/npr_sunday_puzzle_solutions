;; http://www.npr.org/2017/07/09/535952970/sunday-puzzle-switch-it-up

;; Take a certain 7-letter word. Remove the first letter and you get a 
;; 6-letter synonym of that word. And the letter you removed is an 
;; abbreviation for the opposite of both words. What words are these?

(->> (slurp (clojure.java.io/resource "roget.txt"))             
     clojure.string/split-lines                                 
     (map #(clojure.string/split % #"=> " 2))                    
     (map last)                                                                       
     (map #(clojure.string/split % #"\|"))                       
     (map #(filter (comp #{6 7} count) %))
     (map (partial group-by count))
     (map vals)
     (map #(for [seven (first %) six (last %) :when (= (subs seven 1) six)] [seven six]) )
     (remove (partial = '())))
