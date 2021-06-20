; https://www.npr.org/2021/03/07/974377830/sunday-puzzle-pb-and-j-time

; Think of a country with a one-word name. You can rearrange its letters 
; to identify a member of one of our country's armed forces. 
; Who is that, and what's the country?

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Za-z]" ""))

(def countries
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") first))
       (map clojure.string/lower-case)
       (mapv clean-up-word)))

(def words
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (mapv (comp clojure.string/lower-case clean-up-word first))))

(def solution
  (->> (for [c countries
             :let [a (filter (partial anagram? c) words)]
             :when (< 0 (count a))]
         [c a])))
