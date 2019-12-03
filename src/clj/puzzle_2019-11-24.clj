;;  https://www.npr.org/2019/11/24/782295066/sunday-puzzle-made-up-phrases

;; The words WON and SUN rhyme, even though their vowels are different. 
;; Can you name four common, uncapitalized 4-letter words, each of which 
;; has exactly one vowel, and all of which rhyme, even though all four vowels 
;; are different?

(def vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
              "AY" "IY" "ER" "EY" "AW" "OY" "UW"})

(def orthographic-vowels #{\A \E \I \O \U}) ; maybe Y too?

(defn wipe-and-split [s]
  (clojure.string/split (clojure.string/replace s #"\d" "") #" "))


(defn get-nucleus-and-coda [syllables]
  (->> (wipe-and-split syllables)
       (drop-while #(not (contains? vowels %)))
       (apply str)))


(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))


(def four-letter-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (juxt (comp clean-up-word first) (comp identity second)))
       (filter (comp (partial = 4) count first))))


(def rhymed-words-map
  (group-by (comp get-nucleus-and-coda second) four-letter-words))

;; (count rhymed-words-map) -> 1800

(def sets-with-at-least-four-words
  (->> rhymed-words-map
       (filter (comp (partial < 3) count second))
       (map second)
       (map (partial map first)))) 
; ^^ these last two lines help make it easier to read

;; (count sets-with-at-least-four-words) -> 448

(defn has-exactly-one-vowel? [word]
  (->> word
       (map char)
       (filter orthographic-vowels)
       count
       (= 1)))

(def words-with-exactly-one-vowel
  (->> sets-with-at-least-four-words
       (map #(filter has-exactly-one-vowel? %))
       (remove empty?)
       (filter (comp (partial < 3) count))))

(defn four-vowels-in-a-word-set? [word-set]
  (->> word-set
       (apply concat)
       set
       (filter orthographic-vowels)
       count
       (= 4)))

;; (count (filter four-vowels-in-a-word-set? words-with-exactly-one-vowel)) -> 6
