; https://www.npr.org/2024/05/12/1250440016/sunday-puzzle-six-letter-words-with-four-vowels-and-two-consonants

; Think of a well-known seven-letter geographical name in a single 
; word that has just two consonants and yet is pronounced in five syllables.

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

(defn has-five-syllables? [s]
  (->> #" "
       (clojure.string/split s)
       (filter vowels)
       count
       (= 5)))

#_(defn has-two-consonants? [w]
  (= 3 (get (frequencies w) \I)))

(def candidates (->> cmuwords
                     (filter (comp (partial = 7) count first))
                     (filter (comp has-five-syllables? second))
                     ))

