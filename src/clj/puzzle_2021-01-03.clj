; https://www.npr.org/2021/01/03/952835449/sunday-puzzle-new-names-in-2020

; Think of a seven-letter hyphenated word for a kind of cooking. 
; Change the middle letter to get a new word describing a kind 
; of music. What words are these?

(def alphabet
  #{\a \b \c \d \e \f \g \h \i \j \k \l \m
    \n \o \p \q \r \s \t \u \v \w \x \y \z})

(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(defn has-seven-letters? [s]
  (->> s
       clojure.string/lower-case
       (mapv char)
       (filter alphabet)
       count
       (= 7)))

(def candidates
  (->> moby
       (filter (partial re-find #"\-"))
       (filter has-seven-letters?)))
