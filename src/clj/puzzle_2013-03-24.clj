;(ns puzzle_2013-03-24
;  (:refer-clojure :exclude [== - + * /])
;  (:use clojure.core.logic)
;  (:require [clojure.core.logic.fd :as fd]))

;; Mitchell Challenge
;;      (((( _ + _ ) - _ ) x _ ) / _ ) x _  = 18

(run* [q]
  (fresh [a b c d e f]
    (fd/in a b c d e f (fd/interval 1 6))
    (fd/eq
     (= (* (/ (* (- (+ a b) c) d) e) f) 18))
    (fd/distinct [a b c d e f])
    (== q [a b c d e f])))

;; http://www.npr.org/2013/03/24/175144673/finding-the-answers-within

;; Take the four words "salt," "afar," "lava" and "trap." Write them
;; one under the other, and the words will read the same vertically as
;; horizontally. This is a word square of four-letter words. Note that the
;; only vowel in this example square is an A.

;; The object of the challenge is to create a five-letter word square using
;; only common, uncapitalized English words, in which the only vowel in the
;; entire square is A. The word in the center row, and column, is NASAL.

;; SALT
;; AFAR
;; LAVA
;; TRAP

;; http://mattsenior.com/2014/02/using-clojures-core-logic-to-solve-simple-number-puzzles
;; https://github.com/clojure/core.logic/wiki/A-Core.logic-Primer
;; http://www.cs.sfu.ca/CourseCentral/383/tjd/corelogic.html

;; ? ? N ? ?
;; ? ? A ? ?
;; N A S A L
;; ? ? A ? ?
;; ? ? L ? ?

;; a b N d e
;; b c A f g
;; N A S A L
;; d f A h i
;; e g L i j

;; Reference: https://gist.github.com/arnaudsj/d85fdbdcc6826ff65c7a

; (refer-clojure :exclude '[== - + * /])

;; Crosswords: https://gist.github.com/WuHoUnited/4518619

(refer-clojure :exclude [==])

(use 'clojure.core.logic)

(require '[clojure.core.logic.fd :as fd])


(def words
  (->> (slurp "/Users/msszczep1/Scripts/npr_puzzle_scripts/ospd3.txt")
       clojure.string/split-lines
       (filter (comp (partial = 5) count))
       (remove (partial re-find #"[eiou]"))
       (filter (partial re-find #"a"))
       set))

(def letters [\a \b \c \d \f \g \h \j \k \l \m
              \n \p \q \r \s \t \v \w \x \y \z])


(run* [q]
  (fresh [a1 a2 a3 a4 a5
          b1 b2 b3 b4 b5
          c1 c2 c3 c4 c5
          d1 d2 d3 d4 d5
          e1 e2 e3 e4 e5]
         (== q [[a1 a2 a3 a4 a5]
                [b1 b2 b3 b4 b5]
                [c1 c2 c3 c4 c5]
                [d1 d2 d3 d4 d5]
                [e1 e2 e3 e4 e5]])
         (membero a1 a2 a3 a4 a5
                  b1 b2 b3 b4 b5
                  c1 c2 c3 c4 c5
                  d1 d2 d3 d4 d5
                  e1 e2 e3 e4 e5 letters)
         (fd/eq
           (= "nasal" (str c1 c2 c3 c4 c5))
           (= "nasal" (str a3 b3 c3 d3 e3))
           (= b1 a2)
           (= c1 a3)
           (= a4 d1)
           (= a5 e1)
           (= d2 b4)
           (= e2 b5)
           (= e4 d5)
           (= a3 c1 \n)
           (= c2 b3 c4 d3 \a)
           (= c3 \s)
           (= e3 c5 \l)
           (contains? words (str a1 a2 a3 a4 a5))
           (contains? words (str b1 b2 b3 b4 b5))
           (contains? words (str d1 d2 d3 d4 d5))
           (contains? words (str e1 e2 e3 e4 e5))
           (contains? words (str a1 b1 c1 d1 e1))
           (contains? words (str a2 b2 c2 d2 e2))
           (contains? words (str a4 b4 c4 d4 e4))
           (contains? words (str a5 b5 c5 d5 e5)))))

