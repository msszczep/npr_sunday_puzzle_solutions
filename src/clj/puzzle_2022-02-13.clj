; https://www.npr.org/2022/02/13/1079986895/sunday-puzzle-take-a-deep-breath

; Think of a common boy's name and a common girl's name that are pronounced 
; the same even though they have only two letters in common. And if you reverse 
; the boy's name, phonetically you'll get another common girl's name. What names are these?

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))))

(defn two-in-common? [[w1 w2]]
  (= 2 (count (clojure.set/intersection (set w1) (set w2)))))

(defn two-in-common-improved? [[w1 w2]]
  (filter #(=)))

(def groups
  (->> cmuwords
       (group-by second)
       (remove (comp (partial = 1) count second))
       (mapv second)
       (mapv (partial mapv first))
       (filter (comp (partial = 2) count))
       (filter two-in-common?)))




