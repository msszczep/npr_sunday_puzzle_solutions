;; http://www.npr.org/2015/11/15/456025971/thank-heavens-for-the-thesaurus-youll-need-one-for-this-puzzle

;; Think of a word that contains three consecutive letters of the alphabet together — like CANOPY,
;; which contains NOP. Change these three letters to one new letter to make a synonym of the first
;; word. What words are these?

(def threes (map #(apply str %)
                 (partition 3 1 "abcdefghijklmnopqrstuvwxyz")))

(def words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines))

(def three-words
  (->> (for [w words
             three threes]
         (when (re-seq (re-pattern three) w) w))
       (remove nil?)))

(def three-words-asterisks
  (->> (for [tw three-words
             three threes]
         (clojure.string/replace tw (re-pattern three) "+"))
       (filter #(re-find #"\+" %) )))

(clojure.string/replace "+ntman" "+" "\\w")

(re-seq (re-pattern "^\\wntman$") "antman")

(def words-to-maps
  (zipmap three-words three-words-asterisks))

(defn run-final-regex [r word]
   (let [rw (clojure.string/replace (last r) "+" "\\w")]
     (when (re-seq (re-pattern (str "^" rw "$")) word)
       [(first r) word])))


(def synonym-pairs
  (remove nil? (for [w words
                     wm words-to-maps]
                 (run-final-regex wm w))))


(take 200 synonym-pairs)















;; defeat beat
