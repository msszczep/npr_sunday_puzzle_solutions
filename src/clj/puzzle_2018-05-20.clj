; https://www.npr.org/2018/05/20/612119230/sunday-puzzle-not-as-advertised

;Take the title of a famous Hollywood flop. Change an A to an R, then 
; rearrange the letters to spell a famous box office hit — which went 
; on to spawn sequels. What films are these?

(def answer
  (let [movies
          (->> "resources/movies3.txt"
               slurp
               clojure.string/split-lines
               (map clojure.string/lower-case)
               (map #(clojure.string/replace % #" " "")))]
    (letfn [(freqs-without-a [movie]
              (let [f-1 (merge-with - (frequencies movie) {\a 1})]
               (if (zero? (get f-1 \a))
                 (dissoc f-1 \a)
                 f-1)))]
      (for [film-with-a (filter (partial re-find #"a") movies)
            film-with-r (filter (partial re-find #"r") movies)
            :when (= (frequencies film-with-r)
                     (merge-with + {\r 1}
                                   (freqs-without-a film-with-a)))]
        [film-with-a film-with-r]))))

