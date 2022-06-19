; https://www.npr.org/2022/06/19/1106060697/sunday-puzzle-triple-purpose

; Think of two famous people — one from business and one from entertainment 
; — whose last names are anagrams of each other. Now take their first names, 
; drop the last letter of each of them, and put the result together, without 
; rearranging, and you'll get the full first name of a famous fictional 
; character. Who are these people?

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def solset
  (filter (comp (partial = 2) count) who))

(def s
  (->> solset
       (group-by (comp frequencies clojure.string/lower-case first))
       (filter (comp (partial < 1) count val))
       (mapv second)
       (filter (comp (partial < 1) count distinct (partial mapv first)))))


