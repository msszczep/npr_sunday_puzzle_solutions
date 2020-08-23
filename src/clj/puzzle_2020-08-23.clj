; https://www.npr.org/2020/08/23/905057090/sunday-puzzle-low-and-inside

; Think of a place on earth with a four-word name. Take the third word. 
; Advance three of its letters to the next letter of the alphabet, so 
; A would become B, B would be come C, etc.
; You'll get the fourth word in the name. What place is this?

(def names-to-use
   (->> "resources/enwiki-20200820-all-titles-clean2.txt"
        slurp
        clojure.string/split-lines
        (mapv clojure.string/lower-case)))

(defn word3-word4-equal? [s]
  (let [[_ _ w3 w4] (clojure.string/split s #"_")]
    (and (< 3 (count w3))
         (= (count w3)
            (count w4)))))

(defn remove-matching-characters [s]
  (let [[_ _ w3 w4] (clojure.string/split s #"_")]
    (->> (interleave w3 w4)
         (partition 2)
         (remove #(= (first %) (last %)))
         count
         (= 3))))

; consec-letters-to-watch:
; (set (concat (partition 2 1 "zyxwvutsrqponmlkjihgfedcba") (partition 2 1 "abcdefghijklmnopqrstuvwxyz")))

(defn are-letters-consecutive? [s]
  (let [[_ _ w3 w4] (clojure.string/split s #"_")
        consec-letters-to-watch #{[\p \o] [\i \h] [\q \r] [\t \s] [\n \o] [\d \c] [\s \r] [\g \f] [\w \x] [\j \k] [\q \p] [\r \s] [\k \l] [\e \d] [\b \c] [\x \w] [\b \a] [\m \n] [\a \b] [\t \u] [\p \q] [\u \v] [\x \y] [\f \g] [\d \e] [\o \n] [\m \l] [\l \m] [\i \j] [\e \f] [\v \u] [\z \y] [\n \m] [\f \e] [\w \v] [\k \j] [\h \g] [\u \t] [\o \p] [\v \w] [\y \x] [\s \t] [\j \i] [\l \k] [\r \q] [\c \d] [\g \h] [\c \b] [\y \z] [\h \i]}]
    (->> (interleave w3 w4)
         (partition 2)
         (filter consec-letters-to-watch)
         count
         (= 3))))

(def answer
  (->> names-to-use
       (filter word3-word4-equal?)
       (filter remove-matching-characters)
       (filter are-letters-consecutive?)))
