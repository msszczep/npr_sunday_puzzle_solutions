; https://www.npr.org/2020/03/22/819678443/sunday-puzzle-hinkity-pinkity

; Many famous people's names contain three pairs of double letters, 
; like Johnny Appleseed and the actress Jennifer Connelly. But there 
; are two famous fiction writers — one male, one female — whose names 
; have four pairs of double letters. The male writer is Tennessee 
; Williams. Who is the popular female writer?

(def writers
   (->> "resources/writers.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))
        (remove (partial re-find #"__"))))


(defn four-double-letters? [writer]
  (->> writer
       (partition-by identity)
       (map count)
       (filter (partial < 1))
       count
       (= 4)))


(filter four-double-letters? writers)




