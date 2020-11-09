(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(def roget
  (->> "resources/roget_clean.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"=> "))
       (map last)
       (map #(clojure.string/split % #"\|"))
       (apply concat)))

(def cliches
  (->> "resources/clichenet.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)))

(defn only-npr? [word]
  (->> ""
       (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]")
       frequencies
       keys
       (remove #{\a \e \i \o \u})
       set
       (= #{\n \p \r})))

(defn npr-two? [word]
  (let [w-freq (frequencies (clojure.string/lower-case word))]
    (and (= 2 (get w-freq \n))
         (= 2 (get w-freq \p))
         (= 2 (get w-freq \r)))))

(defn npr? [word]
  (and (npr-two? word) 
       (only-npr? word)))
