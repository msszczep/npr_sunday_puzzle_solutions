; https://www.npr.org/2021/09/05/1034331240/sunday-puzzle-two-consonants

; Name a famous person (8,4). The last name is a regular uncapitalized 
; word with a single vowel. Change that vowel to make a new word that 
; is humorously defined by the person's first name. Who is it?

(def vowels #{\a \e \i \o \u})

(defn one-vowel? [s]
  (->> s
       (map char)
       (filter vowels)
       count
       (= 1)))

(defn remove-vowels [w]
  (clojure.string/replace w #"[aeiou]" "_"))

(def four-letter-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 4) count))
        (filter one-vowel?)
        (group-by remove-vowels)
        (remove (comp (partial = 1) count last))
        (map last)
        flatten
        set))

(def eight-letter-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 8) count))
        set))

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def who2
  (->> "resources/candidates_2021-09-05.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       set
       sort
       (mapv #(clojure.string/split % #"_"))))

(def solution-set
  (->> who
       (filter (comp (partial = 2) count))
       (filter (comp (partial = 8) count last))
       (filter (comp (partial = 4) count first))
       (filter (comp one-vowel? first))))

(def solution-set2
  (->> who2
       (filter (comp one-vowel? last))
       (filter (comp four-letter-words last))
       (remove (comp eight-letter-words first))))


