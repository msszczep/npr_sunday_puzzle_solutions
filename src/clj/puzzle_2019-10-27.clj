; https://www.npr.org/2019/10/27/773786791/sunday-puzzle-the-3-bs

; Think of a familiar two-word phrase — 5 letters in each word — that 
; might be something you'd write in a letter. The first and last 
; letters are the same. The 3rd and 8th letters are the same. The 4th 
; and 7th letters are the same. And the middle two letters are 
; consecutive in the alphabet. What phrase is it?

(def five-letter-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 5) count))))


(def next-letter
  {\a \b, \b \c, \c \d, \d \e, \e \f, \f \g, \g \h,
   \h \i, \i \j, \j \k, \k \l, \l \m, \m \n, \n \o,
   \o \p, \p \q, \q \r, \r \s, \s \t, \t \u, \u \v,
   \v \w, \w \x, \x \y, \y \z})


(def solution
  (for [e1 five-letter-words
        e2 five-letter-words
        :let [l1 (map char e1)
              l2 (map char e2)]
        :when (and (not= e1 e2)
                   (= (nth l1 0) (nth l2 4))
                   (= (nth l1 2) (nth l2 2))
                   (= (nth l1 3) (nth l2 1))
                   (= (next-letter (nth l1 4)) 
                      (nth l2 0)))]
    [e1 e2]))
