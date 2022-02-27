; https://www.npr.org/2022/02/06/1078250432/sunday-puzzle-terrible-twos

; What language in seven letters can be spelled using the letters 
; on three consecutives keys on a telephone? It's a language you 
; would probably recognize, but not one that many people can speak.

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 7) count))
       set))

(def letters-to-phone-keypad
  {\a 2, \b 2, \c 2, \d 3, \e 3, \f 3, \g 4,
   \h 4, \i 4, \j 5, \k 5, \l 5, \m 6, \n 6,
   \o 6, \p 7, \q 7, \r 7, \s 7, \t 8, \u 8,
   \v 8, \w 9, \x 9, \y 9, \z 9})

(defn are-numbers-consecutive? [s]
  (let [t (sort s)]
    (= t [(first t) (inc (first t)) (+ 2 (first t))])))

(defn map-letters-to-digits [word]
  (let [r (->> word
               (mapv letters-to-phone-keypad)
               distinct)]
    (and (= 3 (count r))
         (are-numbers-consecutive? r))))

(def solution 
  (filter map-letters-to-digits cmuwords))

