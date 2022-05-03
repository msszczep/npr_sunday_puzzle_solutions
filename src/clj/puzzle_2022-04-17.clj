; https://www.npr.org/2022/04/17/1093198273/sunday-puzzle-things-in-common

; Name a vehicle in two words — 4 letters in the first, 5 letters in the 
; last. Move the second letter of the last word into the second position 
; of the first word. The result phonetically will name a popular figure 
; from legend. Who is it?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

; time more enwiki-20200820-all-titles-clean.txt | egrep '^[a-zA-Z]{4}_[a-zA-Z]{5}$' > 45.txt

(def forty-five
  (->> "resources/45.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"_" 2))
       (mapv (comp clojure.string/lower-case first))
       set
       ))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(def threes
  (set (filter (comp (partial = 3) count) cmu-words)))

(def fours
   (filter (comp (partial = 4) count) cmu-words))

(defn transform-word [w]
  (let [p1 (subs w 0 1)
        p2 (subs w 2)]
    (str p1 p2)))

(def candidates
  (->> fours
       (mapv (juxt identity transform-word (fn [x] (threes (transform-word x)))))
       (filter (comp not nil? last))
       (filter (comp forty-five first))
       sort))


