; https://www.npr.org/2022/07/31/1114717889/sunday-puzzle-or-shall-we-say-sunday-sell-ebration

; Name a famous person in American television — 6 letters in the first name, 4 letters 
; in the last. Switch the last letter of the first name with the first letter of the last. 
; Then reverse the order of the two modified names. You'll get a phrase meaning 
; "almost typical." What is it?

; time egrep '^[A-Za-z]{6}_[A-Za-z]{4}$' enwiki-20200820-all-titles-clean.txt > six_four.txt

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def six-four
  (->> "resources/six_four.txt"
       slurp
       clojure.string/split-lines))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(def ospd
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(defn transform-name [n]
  (let [[w1 w2] (clojure.string/split (clojure.string/lower-case n) #"_" 2)
        t1 (str (subs w1 0 5) (first w2))
        t2 (str (last w1) (subs w2 1))]
    [w1 w2 t1 t2]))

(defn cmu-word? [[_ _ a b]]
  (and (cmu-words a) 
       (cmu-words b)
       (ospd a)
       (ospd b)
       (not= (last a) (first b))))

(def candidates
  (->> six-four
       (mapv transform-name)
       (filter cmu-word?)
       distinct))
