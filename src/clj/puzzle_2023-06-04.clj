; https://www.npr.org/2022/07/31/1114717889/sunday-puzzle-or-shall-we-say-sunday-sell-ebration

; Name a famous singer (6,4).  Remove the last letter of the first name and the first letter 
; of the last name.  The result, reading left to right, is a word for some singing. 
; What is it?

; time egrep '^[A-Za-z]{6}_[A-Za-z]{4}$' enwiki-20200820-all-titles-clean.txt > 64.txt

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def six-four
  (->> "resources/64.txt"
       slurp
       clojure.string/split-lines))

(def ospd
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(defn transform-name [n]
  (let [[w1 w2] (clojure.string/split (clojure.string/lower-case n) #"_" 2)
        t1 (str (subs w1 0 5) (subs w2 1))]
    [w1 w2 t1]))

(def candidates
  (->> six-four
       (mapv transform-name)
       (filter (comp ospd last))
       distinct))
