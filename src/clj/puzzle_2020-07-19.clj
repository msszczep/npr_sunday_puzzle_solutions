; https://www.npr.org/2020/07/19/892634123/puzzle-fast-and-famous

; Think of a six-letter word for something you might wear. 
; Insert an "O" in the exact middle, and you'll get a phrase 
; meaning "Not aware." What is it?

(def clothes
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "06") last))
       (filter (comp (partial = 6) count first))
       (remove (comp (partial re-find #"\_") first))
       (map first)
       (map clojure.string/lower-case)
       set))

(defn put-o-in-middle [s]
  (str (subs s 0 3) "o" (subs s 3)))

(clojure.pprint/pprint (map (juxt identity put-o-in-middle) 
                            (drop 500 (sort clothes))))
