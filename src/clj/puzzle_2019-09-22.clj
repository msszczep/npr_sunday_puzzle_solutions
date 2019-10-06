; https://www.npr.org/2019/09/22/763085688/sunday-puzzle-flipping-rs-to-s-s

; Think of an adjective in five letters in two syllables. The first 
; syllable phonetically sounds like a synonym of the full, five-letter word. 
; And strangely these two words have no letters in common. What words are these?


(def five-letter-two-syllable-words
  (let [letters (set "ABCDEFGHIJKLMNOPQRSTUVWXYZ")]
    (->> "resources/cmudict-0.7b.txt"
         slurp
         clojure.string/split-lines
         (map #(clojure.string/split % #"  "))

         (filter (comp (partial = 2) count #(re-seq #"\d" %) second)) 
         ; find two-syllable words
        
         (filter (comp (partial = 5) count first))
         ; find five-letter words

         (filter (comp (partial every? letters) first))
         ; find only words with all letters, i.e., no punctuation

         (map #(->> % first clojure.string/lower-case))
         ; force to lowercase

         set)))


(def five-letter-adjectives
  (->> "resources/data_cleaned.adj"
       slurp
       clojure.string/split-lines
       (map #(-> % 
                 (clojure.string/split #" ")
                 (nth 4)))
       (filter (comp (partial = 5) count))
       set))

(def answer
  (clojure.set/intersection five-letter-adjectives five-letter-two-syllable-words))


