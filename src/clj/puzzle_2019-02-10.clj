; https://www.npr.org/2019/02/10/692431826/sunday-puzzle-3rd-o

; Name a well-known rock band in three words. Change the first 
; and third letters to the first and third letters of the alphabet 
; — that is, A and C. You can rearrange the result to name another 
; famous rock band in three words. What is it?

(def some-music-data
  (->> "resources/million-song-database-musicacts.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"<SEP>"))))


(defn three-words? [band]
  (->> (clojure.string/split band #" ")
       count
       (= 3)))


(def bands
  (->> some-music-data
       (map last)
       (filter three-words?)
       (map clojure.string/lower-case)
       set))


(def candidate-bands
  (concat (filter (partial re-find #"^.*a.*c.*$") bands) (filter (partial re-find #"^.*c.*a.*$") bands)))

(defn abs [n] (max n (- n)))


(defn compare-bands [b1 b2]
  (->> (merge-with - (frequencies b1) (frequencies b2))
       vals
       (map abs)
       (apply +)
       (= 4)))

(def pairs 
  (for [b bands
        cb candidate-bands
        :when (and (compare-bands b cb)
                   (= (count b) (count cb)))]
    [b cb]))

(def p-set (set pairs))
