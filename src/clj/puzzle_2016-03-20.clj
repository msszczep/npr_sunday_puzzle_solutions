;; http://www.npr.org/2016/03/20/471102829/first-and-last-two-letters-are-key-to-solve-this-puzzle-thats-not-so-easy

;; Think of a common nine-letter word that contains five consecutive consonants.
;; Take three consecutive consonants out of these five and replace them with vowels
;; to form another common nine-letter word. What is it?

(def words
  (->> ;(slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ni2.txt")
       (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/cmudict-0.7b.txt")
       clojure.string/split-lines
       (map #(first (clojure.string/split % #" ")))
    ))

(defn is-lower-case? [s]
  (= (first (map char (clojure.string/lower-case s))) s))

(defn is-consonant? [s]
  (nil? (#{\A \E \I \O \U \Y} s)))

(defn is-vowel? [s]
  (false? (is-consonant? s)))

; for each word, find a word that takes 3 consonants with 3 vowels

; NIGHTCLUB; CORKSCREW; OFFSPRING; ERSTWHILE; NORTHSTAR; STRENGTHS;
; STRENGTHS
; STRENUOUS

(map #(map is-vowel? %) (partition 3 1 "BEAU"))

; more cmudict-0.7b.txt | egrep '[AEIOU]{3}' | awk '{if (length($1) == 9) {print $1}}' | more

(->> (partition 3 1 "ABSTAINED")

     )

(map is-vowel? '(\A \B \S))

(->> words
     (filter #(= 9 (count %)))
;     (filter #(every? is-lower-case? %))
     (filter #(contains? (set (map count (partition-by is-consonant? %))) 3))
     (remove #(= "(1)" (subs % 6)))
     (remove #(= "(2)" (subs % 6)))
     (remove #(= "(3)" (subs % 6)))
     (remove #(re-find #"'" %))
     (remove #(re-find #"_" %))
;     count
     )


(->> words
     (filter #(= 9 (count %)))
     )
