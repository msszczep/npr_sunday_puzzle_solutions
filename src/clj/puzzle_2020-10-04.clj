; https://www.npr.org/2020/10/04/919692327/sunday-puzzle-game-of-words

; Think of an 8-letter word with three syllables that contains the letter 
; "I" in each syllable — but, strangely, doesn't contain a single "I" sound, 
; either long or short. The answer is not a plural. What word is it?

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

(defn has-three-syllables? [s]
  (->> #" "
       (clojure.string/split s)
       (filter vowels)
       count
       (= 3)))

(defn has-three-i? [w]
  (= 3 (get (frequencies w) \I)))

(defn has-three-lower-i? [w]
  (= 3 (get (frequencies w) \i)))

(defn has-no-i-sounds? [s]
   (->> #" "
       (clojure.string/split s)
       (filter vowels)
       (remove #{"IH" "IY"})
       count
       (= 3)))

(def candidates (->> cmuwords
                     (filter (comp (partial = 8) count first))
                     (filter (comp has-three-i? first))
                     (filter (comp has-three-syllables? second))
                     (filter (comp has-no-i-sounds? second))
                     ))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 8) count))
       (filter has-three-lower-i?)
))


(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 8) count))
       (filter has-three-lower-i?)
))

(def enwiki8
  (->> "resources/enwiki8.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 8) count))
       (filter has-three-lower-i?)
))
