;; http://www.npr.org/2016/08/28/491699329/3-3-8-it-does-in-this-weeks-puzzle

;; What one-syllable word in 7 letters becomes a four-syllable word by
;; inserting the consecutive letters IT somewhere inside?


(let [vowels #{"AA" "AH" "EH" "IH" "OW" "UH" "AE" "AO"
               "AY" "IY" "ER" "EY" "AW" "OY" "UW"}
      ; "vowels" reflect phonemes, and correlate with syllables

      letters (->> (map char "ABCDEFGHIJKLMNOPQRSTUVWXYZ") set)
      ; now with all 26 English letters! :-)

      terms
      (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
           clojure.string/split-lines
           ; take the CMU pronunciation dictionary, split on each line

           (map #(clojure.string/replace-first % #" " ""))
           ; formatting cleanup: remove double-space separating lexeme from phonemes

           (map #(clojure.string/split % #" " 2))
           ; separate lexeme from phoneme into different vector elements

           (map (juxt first #(clojure.string/replace (second %) #"\d" "")))
           ; remove digits from vowel phonemes, which reflect stress

           (map (juxt first #(clojure.string/split (second %) #" "))))
           ; split each phoneme for later analysis

      seven-letter-one-syllable-words
      (->> terms
           ; for each term...

           (filter (comp (partial = 1) count (partial filter vowels) second))
           ; filter words that are monosyllabic

           (filter (comp (partial = 7) count (partial filter letters) first))
           ; and that include seven letters

           (filter (comp (partial = 7) count first))
           ; and that are seven _characters_ long

           (map first)
           ; keep just the word, drop the phonemes

           (map (juxt identity (comp
                                 (partial merge-with + {\I 1 \T 1})
                                 frequencies
                                 identity))))
           ; and create a new entry with each word and its frequency map,
           ; adding entries for "IT" to the frequency map

      nine-letter-four-syllable-words
      (->> terms
           ; for each term...

           (filter (comp (partial = 4) count (partial filter vowels) second))
           ; filter words that have four syllables

           (filter (comp (partial = 9) count (partial filter letters) first))
           ; and that include nine letters

           (filter (comp (partial = 9) count first))
           ; and that are nine _characters_ long

           (map first)
           ; keep just the word, drop the phoneme

           (map (juxt identity frequencies)))]
           ; and create a new entry with each word and its frequency map

        (->> (concat nine-letter-four-syllable-words seven-letter-one-syllable-words)
             ; the real work starts here: combine both into a single big seq

             (group-by second)
             ; group by the respective frequencies

             (filter (comp (partial = 2) count second))
             ; keep only those frequencies that have exactly two entries

             (map (comp (partial map first) second))
             ; show just the words in each pair

             (filter #(not= (count (first %)) (count (second %))))))
             ; and remove those pairs whose words have the same length
