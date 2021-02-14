; https://www.npr.org/2021/01/31/962412357/sunday-puzzle-game-of-words

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Za-z\-]" "")))

(def actors
  (->> "resources/actors.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #"_"))))

(def actors2
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def bible
  (->> "resources/bible-books.txt"
       slurp
       clojure.string/split-lines
       set))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(defn candidate? [[lname fname]]
  (and (contains? bible fname)
       (not (empty? (filter (partial anagram? (clean-up-word lname)) 
                            (map clean-up-word bible))))))

(def solution
  (filter candidate? actors2))
