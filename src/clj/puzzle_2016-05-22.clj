;; http://www.npr.org/2016/05/22/478980646/straight-out-of-the-funny-papers-here-are-a-few-cartoon-conundrums

;; Name a common household item in 6 letters. Change the middle two letters to a P,
;; and you'll get the 5-letter last name of a famous person who professionally used
;; that item. What's the item, and who's the person?

(let [last-names
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
                                                   ;; CMU list
       clojure.string/split-lines                  ;; split lines
       (map #(clojure.string/split % #" "))        ;; split on line
       (map first)                                 ;; get words
       (map clojure.string/lower-case)             ;; turn to lower-case
       set                                         ;; make into set
     )]
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/wordnet_dict/data_cleaned.noun")
                                                 ;; Wordnet list of nouns
       clojure.string/split-lines                ;; split lines
       (map #(clojure.string/split % #" "))      ;; split on line
       (map (juxt #(nth % 4) second))            ;; get lexicographer contents
       (filter (comp (partial = "06") last))     ;; get inanimate objects
       (map first)                               ;; remove lexicographer contents
       (filter (comp (partial = 6) count))       ;; get list of 6-letter words
       (remove (partial re-find #"\_"))          ;; remove entries with underscores
       (remove (partial re-find #"^[A-Z]"))      ;; remove proper nouns
       (map (juxt identity
             #(str (subs % 0 2) "p" (subs % 4))))
                                                 ;; transform to five-letter name
       (filter (comp last-names last))))         ;; filter names entries

