;; http://www.npr.org/2017/04/02/522357113/april-come-she-will-with-puzzles

;; Think of four 4-letter proper names that are all anagrams of each other.
;; Two of them are first names — one male and one female. The other two are
;; well-known geographical names. What names are these?

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

