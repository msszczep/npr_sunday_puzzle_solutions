; https://www.npr.org/2023/06/25/1183823383/sunday-puzzle-opposites-attract

; Name a well-known TV character (5,6). Change the first letter 
; of the first name to a Y and read it backward. You'll get a 
; synonym of the character's last name. Who is it?

; time egrep '^[A-Za-z]{5}_[A-Za-z]{6}$' enwiki-20200820-all-titles-clean.txt > 56.txt

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(def words56
  (->> "resources/56.txt"
       slurp
       clojure.string/split-lines
       set))

(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       set))

(defn transform-name [s]
  (apply str (concat (reverse (rest (clojure.string/lower-case (clojure.string/replace s #"_" "")))) [\y])))

(def solution
  (filter ni2 (mapv transform-name words56)))

(defn cut-name [s]
  (first (clojure.string/split (clojure.string/lower-case s) #"_")))

(take 300 (reverse (sort-by val (frequencies (mapv cut-name words56)))))

(def ni2-gt5
  (filter (comp (partial = \y) last) (filter (comp (partial < 3) count) ni2)))

(def solution3
  (for [e (take 10 (mapv transform-name words56))
        f (take 10 ni2-gt5)
        :let [t (distinct (map #(clojure.string/last-index-of e %) f))]
        :when (not= t [nil])]
    [e t]))

; 17987 candidates
(def solution4
  (for [e (mapv transform-name words56)
        f ni2-gt5
        :let [t (clojure.string/last-index-of e f)]
        :when (not (nil? t))
        ]
    [e f t]))

(def cmu11
  (filter (comp (partial = \y) last) (filter (comp (partial = 11) count) cmu-words)))

(filter (set cmu11) (map transform-name words56))
