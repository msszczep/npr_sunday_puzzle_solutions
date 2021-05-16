; https://www.npr.org/2021/05/09/995172987/sunday-puzzle-supermarket-scramble

; Think of a word with six syllables that's spelled with only 11 letters — 
; and the four middle syllables have the same vowel. What word is it?

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt first #(clojure.string/replace (second %) #"\d" "")))
       (map (juxt (comp clean-up-word first) second))))

(def vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
               "AY" "IY" "ER" "EY" "AW" "OY" "UW"})

(defn has-six-syllables? [s]
  (->> #" "
       (clojure.string/split s)
       (filter vowels)
       count
       (= 6)))

(def candidates (->> cmuwords
                     (filter (comp (partial = 11) count first))
                     (filter (comp has-six-syllables? second))))

(defn has-four-identical-vowels? [s]
  (contains? (set (mapv #(get (frequencies s) %) #{\A \E \I \O \U})) 4))

(filter has-four-identical-vowels? (map first candidates))

