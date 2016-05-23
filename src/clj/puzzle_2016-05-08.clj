;; http://www.npr.org/2016/05/08/477092627/want-to-find-a-synonym-better-get-to-shufflin

;; Name something in 11 letters that's a common household item. You can
;; rearrange the first six letters to form a synonym of a word spelled
;; by the middle three letters.  What is the item, and what are the words?

;; Reference: http://wordnet.princeton.edu/wordnet/man/lexnames.5WN.html

(let [words (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
                 clojure.string/split-lines)
      three-letter-words (set (filter (comp (partial = 3) count) words))
      six-letter-frequencies (set
                               (map frequencies
                                    (filter (comp (partial = 6) count) words)))]
  (->>
    (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/wordnet_dict/data_cleaned.noun")
                                                                  ;; Wordnet list of nouns
    clojure.string/split-lines                                    ;; split lines
    (map #(clojure.string/split % #" "))
    (map (juxt #(nth % 4) second))                                ;; get lexicographer contents
    (filter (comp (partial = "06") last))                         ;; get human-made objects
    (filter (comp (partial = 11) count first))                    ;; get list of 11-letter words
    (remove (comp (partial re-find #"\_") first))                 ;; remove entries with underscores
    (map first)
    (map (juxt identity #(subs % 0 6) #(subs % 4 7)))             ;; get substrings (can't use partial?)
    (filter (comp three-letter-words (partial last)))             ;; filter valid three-letter words
    (filter (comp six-letter-frequencies
                  frequencies
                  (partial second)))                              ;; filter anagrams of 6-letter words

    ))


