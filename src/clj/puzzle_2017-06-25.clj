;; Take the name KIM KARDASHIAN.  Rearrange the letters to get the last name
;; of a famous actress along with a famous one-named singer.  Who are these
;; people?

(let [words (->> (slurp (clojure.java.io/resource "cmudict-0.7b.txt"))
                 clojure.string/split-lines
                 (map #(clojure.string/split % #" " 2))
                 (map first)
                 (filter (partial re-find #"^[KIMARDSHN]+$")))
      kk-frequencies (frequencies "KIMKARDASHIAN")]
  (for [w1 words
        w2 words
        :when (and (= kk-frequencies (frequencies (str w1 w2)))
                   (not= w1 w2))]
   [w1 w2]))
