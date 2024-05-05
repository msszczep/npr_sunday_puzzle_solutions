; https://www.npr.org/2024/05/05/1248990211/sunday-puzzle-april-showers-bring-may-flowers

; Think of three common six-letter words that have vowels in the second and fifth 
; positions. The last five letters of the words are the same. Only the first letters 
; differ. And none of the words rhyme with either of the others. What words are they?

(def sixes
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 6) count))
        set))

(def vowels #{\a \e \i \o \u})

(defn has-right-vowels? [w]
  (let [[a b c d e] (map char w)]
    (and (contains? vowels a) 
         (contains? vowels d))))

(def entries 
  (->> sixes
       (group-by #(subs % 1 6))
       (filter (comp (partial < 2) count second))
       (filter (comp has-right-vowels? first))
       sort))

