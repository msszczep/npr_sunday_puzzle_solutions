
(->> (slurp (clojure.java.io/resource "cmudict-0.7b.txt"))
     clojure.string/split-lines
     (map #(clojure.string/split % #"  "))
     (group-by last)
     (filter (comp (partial < 1) count last))
     (map (comp (partial map first) last))
     (sort-by first)
;     count
     )


