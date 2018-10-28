; https://www.npr.org/2018/10/21/659245659/sunday-puzzle-find-the-missing-link

; Take the 9 letters of BEER MOUTH. Arrange them in a 3x3 array 
; so that the three lines Across, three lines Down, and both 
; diagonals spell common 3-letter words. Can you do it?

(def beermouth-freq
  (frequencies "beermouth"))

(defn check-frequencies [word]
  (let [freqs-to-use (frequencies word)
        letters-to-use (keys freqs-to-use)]
    (every? (fn [l] (>= (get beermouth-freq l) 
                        (get freqs-to-use l))) 
            letters-to-use)))


(def three-let-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 3) count))
        (filter (partial re-find #"^[bermouth]+$"))
        (filter check-frequencies) ; filter so that only words with set from "beermouth"
;        (map (comp seq char-array))
        set))

(defn find-solo [letter pos]
  (filter (fn [w] (= letter (nth w pos))) three-let-words))

(def solo-r
  (for [letter [\b \e \r \m \o \u \t \h]
        n [1]]
    [letter n (find-solo letter n)]))

; OHM
; RUE
; BET


(def two-let-words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 2) count))
        (filter (partial re-find #"^[bermouth]+$"))
        (filter check-frequencies) ; filter so that only words with set from "beermouth"
        (map (comp seq char-array))
        set
        ))

(use 'clojure.core.logic)

(require '[clojure.core.logic.fd :as fd])

(def ltu (into [] (keys beermouth-freq)))

(def ltu2 [\r \o \h])

(run* [q]
  (membero q ltu2)
  (membero q [\h \e \o])
)

(run* [q]
  (fresh [a1 a2]
    (clojure.core.logic/== q [a1 a2])
    (membero a1 [\a])
    (membero a2 [\b \c])
    (membero (seq [a1 a2]) [[\a \b] [\a \d]])
))


(run* [q]
  (fresh [a1 a2
          b1 b2]
         (clojure.core.logic/== q 
               [[a1 a2]
                [b1 b2]])
         (membero a1 ltu2)
         (membero a2 ltu2)
         (membero b1 ltu2)
         (membero b2 ltu2)
         #_(clojure.core.logic/== 
           (seq (set [a1 a2 b1 b2]))
           (seq #{\e \h \o \r}))
         (membero (seq [a1 a2]) ['(\h \h) '(\h \o)])
         #_(membero (seq [b1 b2]) two-let-words)
         #_(membero (seq [a1 b1]) two-let-words)
         #_(membero (seq [a2 b2]) two-let-words)))


(run* [q]
  (fresh [a1 a2 a3
          b1 b2 b3
          c1 c2 c3]
         (clojure.core.logic/== q [[a1 a2 a3]
                  [b1 b2 b3]
                  [c1 c2 c3]])
         (membero a1 ltu)
         (membero a2 ltu)
         (membero a3 ltu)
         (membero b1 ltu)
         (membero b2 ltu)
         (membero b3 ltu)
         (membero c1 ltu)
         (membero c2 ltu)
         (membero c3 ltu)
         #_(eq (into [] (sort [a1 a2 a3 b1 b2 b3 c1 c2 c3]))
             [\b \e \e \h \m \o \r \t \u])
         (membero (str a1 a2 a3) three-let-words)
         (membero (str b1 b2 b3) three-let-words)
         (membero (str c1 c2 c3) three-let-words)
         (membero (str a1 b1 c1) three-let-words)
         (membero (str a2 b2 c2) three-let-words)
         (membero (str a3 b3 c3) three-let-words)
         (membero (str a1 b2 c3) three-let-words)
         (membero (str a3 b2 c1) three-let-words)))


