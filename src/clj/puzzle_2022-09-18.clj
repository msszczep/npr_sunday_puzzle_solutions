; https://www.npr.org/2022/09/18/1123647605/sunday-puzzle-categories-galore

; If you squish the lowercase letters "r" and "n" together, they look 
; like an "m." Think of a word that ends in the consecutive letters 
; "r-n." Squish them together to get a homophone of a synonym of the 
; first word. What words are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(def rns 
  (filter (partial re-matches #".*rn$") cmu-words))

(defn change-to-m [w]
  (clojure.string/replace w #"rn$" "m"))

(filter (and (comp words first) (comp words last)) 
        (mapv (juxt identity change-to-m) rns))

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

(def r2
  (sort (filter (and (comp cmu-words first) (comp cmu-words last)) 
               (mapv (juxt identity change-to-m) rns))))
