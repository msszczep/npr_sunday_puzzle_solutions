;; http://www.npr.org/2017/01/29/512268693/youre-halfway-there-already

;; Take six different letters. Repeat them in the same order. Then repeat
;; them again — making 18 letters altogether. Finally add "tebasket" at the
;; end. If you have the right letters and you space them appropriately, you'll
;; complete a sensible sentence. What is it?

(def words
  (->> (slurp (clojure.java.io/resource "ospd3.txt"))
       clojure.string/split-lines))
       ;(clojure.string/join "|")))

(def letters [\b \c \d \e \f \g \h \i \j \k \l \m
              \n \o \p \q \r \t \u \v \x \y \z])

(defn check-for-words [s]
  (apply str (re-seq (re-pattern #"hog") words) s))

(def grist (for [first-letter letters
      second-letter letters
      third-letter letters
      :when (and (not= first-letter third-letter)
                 (not= first-letter second-letter)
                 (not= second-letter third-letter))
      :let [six-letters (str first-letter second-letter third-letter "was")
            all-letters (apply str (repeat 3 six-letters))]
      ]
  all-letters
  ))

(->> grist
     (filter (partial re-find #"her"))
     )


(apply str (re-seq (re-pattern #"i") "bcpwasbcpwasbcpwas"))

(->> words
     (filter (partial re-find #"was"))
     (filter (comp (partial = 6) count))
     (filter (comp (partial = 6) count frequencies))
     )

; Her washer was her wastebasket.
