; https://www.npr.org/2021/08/08/1025782728/sunday-puzzle-accented-syllable

; Think of something that gets people moving vertically. Remove the 
; middle two letters, and you get something that moves people 
; horizontally. What two things are these?

(def all-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        set))

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (mapv clojure.string/lower-case)
       set))

(def map-of-lengths
  {14 8, 12 7, 10 6, 8 5, 6 4, 4 3})

(defn remove-middle-two [w]
  (let [l (count w)
        w1 (subs w 0 (dec (/ l 2)))
        w2 (subs w (map-of-lengths l))]
    (str w1 w2)))

(def solution
  (->> cmuwords
       (filter (comp (partial contains? #{6}) count))
       (mapv (juxt identity remove-middle-two))
       (filter (comp all-words last))
))




