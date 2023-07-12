; https://www.npr.org/2022/07/31/1114717889/sunday-puzzle-or-shall-we-say-sunday-sell-ebration

; Name a famous singer (6,4).  Remove the last letter of the first name and the first letter 
; of the last name.  The result, reading left to right, is a word for some singing. 
; What is it?

; time egrep '^[A-Za-z]{6}_[A-Za-z]{6}$' enwiki-20200820-all-titles-clean.txt > 66.txt

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def six-six
  (->> "resources/66.txt"
       slurp
       clojure.string/split-lines))

(def ospd
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(defn transform-name [n]
  (let [[w1 w2] (clojure.string/split (clojure.string/lower-case n) #"_" 2)
        ]
    [n w1 w2]))

(def candidates
  (->> six-six
       (mapv transform-name)
       (filter (comp ospd last))
       distinct))

#_(filter #(= 6 (count (first %)) (count (last %))) who)
