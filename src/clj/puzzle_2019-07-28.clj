; https://www.npr.org/2019/07/28/745971618/sunday-puzzle-high-cs

; This week's challenge comes from listener Andy Blau, a a magician 
; who performs under the name Zoltan the Adequate. He describes the 
; word BEVY as "alphabetically balanced." That is, the first letter, 
; B, is second from the start of the alphabet, and the last letter, 
; Y, is second from the end of the alphabet. Similarly, E and V are 
; each fifth from the ends of the alphabet. Can you think of a 
; six-letter word related to magic that is similarly balanced?

(def words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 6) count))))

(def letters
  {\a 1 \b 2 \c 3 \d 4 \e 5 \f 6 \g 7 \h 8 \i 9 \j 10 \k 11 \l 12 \m 13 
   \n -13 \o -12 \p -11 \q -10 \r -9 \s -8 \t -7 \u -6 \v -5 \w -4 \x -3 \y -2 \z -1})

(defn palindrome? [s]
  (= s (reverse s)))

(defn abs [n]
  (if (neg? n) (- n) n))

; is absolute value palindrome?
; do the values sum to zero?

; (map letters "bevy")

(defn is-balanced? [s]
  (let [n (map letters s)]
    (and (= 0 (apply + n))
         (palindrome? (map abs n)))))

(def answer
  (filter is-balanced? words))
