; https://www.npr.org/2023/03/26/1166069588/sunday-puzzle-the-big-if

; Name two brands of household products, each in three syllables. 
; All of the syllables in the two brands rhyme with each other. 
; That is, the first syllable in the first brand rhymes with the first 
; syllable in the second brand, the second syllables in the two brands 
; rhyme, and the third syllables rhyme. What brand names are these?

(def vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
              "AY" "IY" "ER" "EY" "AW" "OY" "UW"})

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt (comp clean-up-word first) second))
       distinct
       set))

(def ospd-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def ni2-words
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)
       set))

(defn get-pronunciation-info [entry]
  (last (#(clojure.string/split % #" " 2) (last entry))))

(defn wipe-and-split [s]
  (clojure.string/split (clojure.string/replace s #"\d" "") #" "))

(defn get-vowels [entry]
  (filter vowels (wipe-and-split (second entry))))

(def solution
  (->> cmu-words
       (mapv (juxt identity get-vowels))
       (filter (comp (partial = 3) count second))
       (remove (comp ni2-words ffirst))
       (remove (comp ospd-words ffirst))
       (group-by second)
       (filter (comp (partial < 1) count second))
       ))

(def sonority-hierarchy 
  {"AA" 8
   "AE" 8
   "AH" 8
   "AO" 8
   "AW" 8
   "AY" 8
   "B" 2
   "CH" 3
   "D" 2
   "DH" 4
   "EH" 8
   "ER" 8
   "EY" 8
   "F" 3
   "G" 2
   "HH" 3
   "IH" 8
   "IY" 8
   "JH" 3
   "K" 1
   "L" 6
   "M" 5
   "N" 5
   "NG" 5
   "OW" 8
   "OY" 8
   "P" 1
   "R" 6
   "S" 3
   "SH" 3
   "T" 1
   "TH" 4
   "UH" 8
   "UW" 8
   "V" 4
   "W" 7
   "Y" 7
   "Z" 4
   "ZH" 4})

(mapv sonority-hierarchy (clojure.string/split (clojure.string/replace (second (first cmu-words)) #"\d" "") #" "))
