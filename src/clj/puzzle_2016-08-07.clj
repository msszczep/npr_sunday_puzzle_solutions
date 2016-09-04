;; http://www.npr.org/2016/08/07/488997114/head-to-r-i-o-for-this-weeks-puzzle

;; Name a famous Olympics champion past or present -- first and last names.
;; Remove every letter from the name that appears exactly twice. The remaining
;; letters in order will name certain minerals. Who is this Olympics star?

(let [minerals
      (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (remove (partial re-find #"^[A-Z]"))
       set)]
  (letfn [(get-letters-that-occur-twice [name]
                                        (->> (frequencies name)
                                             (filter (comp (partial = 2) #(val %)))
                                             keys
                                             set))]
  (->> (slurp "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/olympians.txt")
                                                   ;; Olympians' database
       clojure.string/split-lines                  ;; split lines
       (map #(clojure.string/replace % #"," ""))   ;; remove commas
       (map #(clojure.string/split % #" "))        ;; split spaces in a line
       (map (juxt second first))                   ;; get names
       (map (partial apply str))                   ;; concatenate first and last name
       (map clojure.string/lower-case)             ;; turn to lower-case
       (map (juxt identity                         ;; remove letters that occur twice
                  #(remove (get-letters-that-occur-twice %) %)))
       (map (juxt first (comp (partial apply str)
                              second)))            ;; change seqs to strings
       (filter (comp minerals second))             ;; filter strings from minerals
       (remove (comp (partial > 5) count second))  ;; filter words less than five letters
       )))

