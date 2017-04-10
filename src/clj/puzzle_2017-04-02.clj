;;

;; 4 four-letter proper names, mutual anagrams:
;; 2 first names (one male, one female); 2 well known geographical names


(let [male-first-names
       (->> (slurp (clojure.java.io/resource "male_first_names_lower.txt"))
            clojure.string/split-lines
            (map clojure.string/upper-case)
            (filter (comp (partial = 4) count))
            set)]
  (letfn [(check-for-males [words]
            (->> (map male-first-names words)
                 (remove nil?)
                 count
                 zero?
                 not))]
    (->> (slurp (clojure.java.io/resource "cmudict-0.7b.txt"))
         clojure.string/split-lines              ;; split lines
         (map #(clojure.string/split % #" "))    ;; split line by space
         (map first)                             ;; get words
         (filter (comp (partial = 4) count))     ;; get list of 4-letter words
         (filter (partial re-find #"[A-Z]{4}"))  ;; remove punctuation
         (group-by frequencies)                  ;; get anagrams
         (filter (comp (partial < 3) count val)) ;; preserve anagram sets > 4
         vals                                    ;; get words
         (filter check-for-males)                ;; get sets with one male name
         sort
     )))

; DANE, EDNA, DEAN, ADEN
