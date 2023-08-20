; https://www.npr.org/2023/08/13/1193621080/sunday-puzzle-fill-in-the-blank

; Name a famous contemporary singer (6,4). The second, fourth, sixth, eighth, and ninth letters, in order, spell a repeated part of a song that everyone knows. What is it?

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
  (let [[_ a _ b _ c _ _ d e _] (map char n)]
    (str a b c d e)))

(def candidates
  (->> six-four
       (mapv (juxt identity (comp transform-name clean-up-word)))
       (filter (comp (partial = 5) count last))
       distinct))
