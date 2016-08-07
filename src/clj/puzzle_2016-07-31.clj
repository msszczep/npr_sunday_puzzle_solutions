;; http://www.npr.org/2016/07/31/488042366/hoping-for-2-of-a-kind-better-mix-things-up-a-bit

;; Take the four four-letter words LIMB, AREA, CORK and KNEE.
;; Write them one under the other, and the four columns will
;; spell four new words LACK, IRON, MERE, and BAKE.

;; LIMB
;; AREA
;; CORK
;; KNEE

;; This is called a double word square. I'd like you to find a
;; double word square with 6-letter words. Specifically, your
;; square must include the words PONIES, ACCEPT, SEARED and CAVIAR.
;; These four words must be among the 12 common, uncapitalized
;; six-letter words in the square. Can you do it?


(use 'clojure.core.logic)
(require '[clojure.core.logic.fd :as fd])


(def letters-list [\a \b \c \d \e \f \g \h \i \j \k \l \m \n \o \p \q \r \s \t \u \v \w \x \y \z])


(run* [q]
      (!= q 2)
      (== q 1))

(run* [a b]
      (== (str a b) "to")
      (membero a letters-list)
      (membero b letters-list))


(def word-arrays
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (filter (comp (partial = 6) count))
       (map (comp seq char-array))
       set))


(def ponies '((\p \o \n \i \e \s)))
(def accept '((\a \c \c \e \p \t)))
(def seared '((\s \e \a \r \e \d)))
(def caviar '((\c \a \v \i \a \r)))

(frequencies [\c \a \v \i \a \r \s \e \a \r \e \d \a \c \c \e \p \t \p \o \n \i \e \s])


;; Take #1: Really brute force

(run* [a1 b1 c1 d1 e1 f1
       a2 b2 c2 d2 e2 f2
       a3 b3 c3 d3 e3 f3
       a4 b4 c4 d4 e4 f4
       a5 b5 c5 d5 e5 f5
       a6 b6 c6 d6 e6 f6]
      (membero (seq [a1 b1 c1 d1 e1 f1]) word-arrays)
      (membero (seq [a2 b2 c2 d2 e2 f2]) word-arrays)
      (membero (seq [a3 b3 c3 d3 e3 f3]) word-arrays)
      (membero (seq [a4 b4 c4 d4 e4 f4]) word-arrays)
      (membero (seq [a5 b5 c5 d5 e5 f5]) word-arrays)
      (membero (seq [a6 b6 c6 d6 e6 f6]) word-arrays)
      (membero (seq [a1 a2 a3 a4 a5 a6]) word-arrays)
      (membero (seq [b1 b2 b3 b4 b5 b6]) word-arrays)
      (membero (seq [c1 c2 c3 c4 c5 c6]) word-arrays)
      (membero (seq [d1 d2 d3 d4 d5 d6]) word-arrays)
      (membero (seq [e1 e2 e3 e4 e5 e6]) word-arrays)
      (membero (seq [f1 f2 f3 f4 f5 f6]) word-arrays)
      (conde
        (membero (seq [a1 b1 c1 d1 e1 f1]) ponies)
        (membero (seq [a2 b2 c2 d2 e2 f2]) ponies)
        (membero (seq [a3 b3 c3 d3 e3 f3]) ponies)
        (membero (seq [a4 b4 c4 d4 e4 f4]) ponies)
        (membero (seq [a5 b5 c5 d5 e5 f5]) ponies)
        (membero (seq [a6 b6 c6 d6 e6 f6]) ponies)
        (membero (seq [a1 a2 a3 a4 a5 a6]) ponies)
        (membero (seq [b1 b2 b3 b4 b5 b6]) ponies)
        (membero (seq [c1 c2 c3 c4 c5 c6]) ponies)
        (membero (seq [d1 d2 d3 d4 d5 d6]) ponies)
        (membero (seq [e1 e2 e3 e4 e5 e6]) ponies)
        (membero (seq [f1 f2 f3 f4 f5 f6]) ponies))
      (conde
        (membero (seq [a1 b1 c1 d1 e1 f1]) accept)
        (membero (seq [a2 b2 c2 d2 e2 f2]) accept)
        (membero (seq [a3 b3 c3 d3 e3 f3]) accept)
        (membero (seq [a4 b4 c4 d4 e4 f4]) accept)
        (membero (seq [a5 b5 c5 d5 e5 f5]) accept)
        (membero (seq [a6 b6 c6 d6 e6 f6]) accept)
        (membero (seq [a1 a2 a3 a4 a5 a6]) accept)
        (membero (seq [b1 b2 b3 b4 b5 b6]) accept)
        (membero (seq [c1 c2 c3 c4 c5 c6]) accept)
        (membero (seq [d1 d2 d3 d4 d5 d6]) accept)
        (membero (seq [e1 e2 e3 e4 e5 e6]) accept)
        (membero (seq [f1 f2 f3 f4 f5 f6]) accept))
      (conde
        (membero (seq [a1 b1 c1 d1 e1 f1]) seared)
        (membero (seq [a2 b2 c2 d2 e2 f2]) seared)
        (membero (seq [a3 b3 c3 d3 e3 f3]) seared)
        (membero (seq [a4 b4 c4 d4 e4 f4]) seared)
        (membero (seq [a5 b5 c5 d5 e5 f5]) seared)
        (membero (seq [a6 b6 c6 d6 e6 f6]) seared)
        (membero (seq [a1 a2 a3 a4 a5 a6]) seared)
        (membero (seq [b1 b2 b3 b4 b5 b6]) seared)
        (membero (seq [c1 c2 c3 c4 c5 c6]) seared)
        (membero (seq [d1 d2 d3 d4 d5 d6]) seared)
        (membero (seq [e1 e2 e3 e4 e5 e6]) seared)
        (membero (seq [f1 f2 f3 f4 f5 f6]) seared))
      (conde
        (membero (seq [a1 b1 c1 d1 e1 f1]) caviar)
        (membero (seq [a2 b2 c2 d2 e2 f2]) caviar)
        (membero (seq [a3 b3 c3 d3 e3 f3]) caviar)
        (membero (seq [a4 b4 c4 d4 e4 f4]) caviar)
        (membero (seq [a5 b5 c5 d5 e5 f5]) caviar)
        (membero (seq [a6 b6 c6 d6 e6 f6]) caviar)
        (membero (seq [a1 a2 a3 a4 a5 a6]) caviar)
        (membero (seq [b1 b2 b3 b4 b5 b6]) caviar)
        (membero (seq [c1 c2 c3 c4 c5 c6]) caviar)
        (membero (seq [d1 d2 d3 d4 d5 d6]) caviar)
        (membero (seq [e1 e2 e3 e4 e5 e6]) caviar)
        (membero (seq [f1 f2 f3 f4 f5 f6]) caviar)))
      (membero a1 letters-list)
      (membero b1 letters-list)
      (membero c1 letters-list)
      (membero d1 letters-list)
      (membero e1 letters-list)
      (membero f1 letters-list)
      (membero a2 letters-list)
      (membero b2 letters-list)
      (membero c2 letters-list)
      (membero d2 letters-list)
      (membero e2 letters-list)
      (membero f2 letters-list)
      (membero a3 letters-list)
      (membero b3 letters-list)
      (membero c3 letters-list)
      (membero d3 letters-list)
      (membero e3 letters-list)
      (membero f3 letters-list)
      (membero a4 letters-list)
      (membero b4 letters-list)
      (membero c4 letters-list)
      (membero d4 letters-list)
      (membero e4 letters-list)
      (membero f4 letters-list)
      (membero a5 letters-list)
      (membero b5 letters-list)
      (membero c5 letters-list)
      (membero d5 letters-list)
      (membero e5 letters-list)
      (membero f5 letters-list)
      (membero a6 letters-list)
      (membero b6 letters-list)
      (membero c6 letters-list)
      (membero d6 letters-list)
      (membero e6 letters-list)
      (membero f6 letters-list)




;; Take 2: Try in a grid

;; ACCEPT
;; **A*O*
;; **V*N*
;; **I*I*
;; SEARED
;; **R*S*


(run* [a1 b1 c1 d1 e1 f1
       a2 b2 c2 d2 e2 f2
       a3 b3 c3 d3 e3 f3
       a4 b4 c4 d4 e4 f4
       a5 b5 c5 d5 e5 f5
       a6 b6 c6 d6 e6 f6]
      (membero (seq [a1 b1 c1 d1 e1 f1]) accept)
      (membero (seq [a2 b2 c2 d2 e2 f2]) word-arrays)
      (membero (seq [a3 b3 c3 d3 e3 f3]) word-arrays)
      (membero (seq [a4 b4 c4 d4 e4 f4]) word-arrays)
      (membero (seq [a5 b5 c5 d5 e5 f5]) seared)
      (membero (seq [a6 b6 c6 d6 e6 f6]) word-arrays)
      (membero (seq [a1 a2 a3 a4 a5 a6]) word-arrays)
      (membero (seq [b1 b2 b3 b4 b5 b6]) word-arrays)
      (membero (seq [c1 c2 c3 c4 c5 c6]) caviar)
      (membero (seq [d1 d2 d3 d4 d5 d6]) word-arrays)
      (membero (seq [e1 e2 e3 e4 e5 e6]) ponies)
      (membero (seq [f1 f2 f3 f4 f5 f6]) word-arrays))



;; Take 3: Try in a filtered grid

(defn make-filtered-word-arrays
  [letter1 position1 letter2 position2]
  (->> word-arrays
       (filter (comp (partial = letter1) #(nth % position1)))
       (filter (comp (partial = letter2) #(nth % position2)))
       (map (comp seq char-array))
       ))

(def word-arrays-as (make-filtered-word-arrays \a 0 \s 4))
(def word-arrays-ce (make-filtered-word-arrays \c 0 \e 4))
(def word-arrays-er (make-filtered-word-arrays \e 0 \r 4))
(def word-arrays-td (make-filtered-word-arrays \t 0 \d 4))
(def word-arrays-ao (make-filtered-word-arrays \a 2 \o 4))
(def word-arrays-vn (make-filtered-word-arrays \v 2 \n 4))
(def word-arrays-ii (make-filtered-word-arrays \i 2 \i 4))
(def word-arrays-rs (make-filtered-word-arrays \r 2 \s 4))


(* 46 359 51 16 57 49 53 65)


(run* [a1 b1 c1 d1 e1 f1
       a2 b2 c2 d2 e2 f2
       a3 b3 c3 d3 e3 f3
       a4 b4 c4 d4 e4 f4
       a5 b5 c5 d5 e5 f5
       a6 b6 c6 d6 e6 f6]
      (membero (seq [a1 b1 c1 d1 e1 f1]) accept)
      (membero (seq [a2 b2 c2 d2 e2 f2]) word-arrays-ao)
      (membero (seq [a3 b3 c3 d3 e3 f3]) word-arrays-vn)
      (membero (seq [a4 b4 c4 d4 e4 f4]) word-arrays-ii)
      (membero (seq [a5 b5 c5 d5 e5 f5]) seared)
      (membero (seq [a6 b6 c6 d6 e6 f6]) word-arrays-rs)
      (membero (seq [a1 a2 a3 a4 a5 a6]) word-arrays-as)
      (membero (seq [b1 b2 b3 b4 b5 b6]) word-arrays-ce)
      (membero (seq [c1 c2 c3 c4 c5 c6]) caviar)
      (membero (seq [d1 d2 d3 d4 d5 d6]) word-arrays-er)
      (membero (seq [e1 e2 e3 e4 e5 e6]) ponies)
      (membero (seq [f1 f2 f3 f4 f5 f6]) word-arrays-td))













;; ACCEPT
;; CLAMOR
;; RAVINE
;; ORIGIN
;; SEARED
;; STRESS
