; FILLME: April 3, 2022

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def surnames
  (->> who
       (filter (comp (partial = 2) count))
       (remove (comp (partial not= 6) count second))
       (mapv (comp (partial apply str) 
                   rest
                   clojure.string/lower-case 
                   first))
       (filter ospd)
       distinct
       sort))











