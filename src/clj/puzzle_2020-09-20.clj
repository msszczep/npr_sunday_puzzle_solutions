; https://www.npr.org/2020/09/20/914780282/sunday-puzzle-accept-some-substitutes

; Take the name of a famous actor — 4 letters in the first name, 5 letters in 
; the last. Spoonerize it. That is, interchange the initial consonant sounds 
; of the first and last names. The result will be two new familiar first names 
; — one male, one female — that start with the same letter ... but that letter 
; is pronounced differently in the two names. Who's the actor?

(def who2
   (->> "resources/who2.txt"
        slurp
        clojure.string/split-lines
        (mapv clojure.string/lower-case)))

(defn has-comma? [n]
  (not (nil? (re-find #"\," n))))

(defn is-four-five? [n]
  (let [[lname fname] (mapv clojure.string/trim 
                            (clojure.string/split n #","))]
    (and (= 4 (count fname))
         (= 5 (count lname)))))

(def candidates
  (->> who2
       (filter has-comma?)
       (filter is-four-five?)))



