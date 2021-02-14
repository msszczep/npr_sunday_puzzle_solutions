; https://www.npr.org/2021/01/24/959931057/sunday-puzzle-sound-it-out

; Think of a hyphenated word you might use to describe a young child 
; that sounds like three letters spoken one after the other.

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def letter-pronunciations 
  ["AH" "B IY" "S IY" "D IY" "IY" "EH F" "JH IY" "EY CH" "AY" "JH EY" "K EY"
   "EH L" "EH M" "EH N" "OW" "P IY" "K Y UW" "AA R" "EH S" "T IY" "Y UW"
   "V IY" "D AH B AH L Y UW" "EH K S" "W AY" "Z IY"])

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt first #(clojure.string/replace (second %) #"\d" "")))
       (map (juxt (comp clean-up-word first) second))))

(defn interpose-spaces [s]
  (apply str (interpose " " s)))

(def three-letter-sets
  (mapv interpose-spaces (combinations letter-pronunciations 3)))

(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(def candidates
  (->> moby
       (filter (partial re-find #"\-"))
       (remove (partial re-find #" "))))

; take each candidate
; split on hyphen
; get each pronunciation
; concatenate those pronunciations
; take 3-sets of the letters
; compare the three-set with the pronounciations
; Check: Do they match?
