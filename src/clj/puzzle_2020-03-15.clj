; https://www.npr.org/2020/03/15/815418488/sunday-puzzle-you-cant-spell-consonant-without-c-or-t

; Think of a well-known entertainer, six letters in the first name, four 
; letters in the last. You can change the first letter of the entertainer's 
; last name to name an animal. And you can change the first letter of the 
; entertainer's first name to get what kind of animal that is.

(def actors
   (->> "resources/actors.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))))


(defn six-and-four? [a]
  (->> #"\_"
       (clojure.string/split a)
       (map count)
       (= [6 4])))


(def actors
   (->> "resources/actors.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))))


(def animals
  (->> (slurp "resources/wordnet_data_cleaned.noun")
                                                                  ;; Wordnet list of nouns
    clojure.string/split-lines                                    ;; split lines
    (map #(clojure.string/split % #" "))
    (map (juxt #(nth % 4) second))                                ;; get lexicographer contents
    (filter (comp (partial = "05") last))                         ;; get animals
    (filter (comp (partial = 4) count first))                    ;; get list of 11-letter words
    (remove (comp (partial re-find #"\_") first))                 ;; remove entries with underscores
    (map first)
    (map clojure.string/lower-case)
    set))


(def filtered-actors
  (filter six-and-four? actors))

(defn get-surname [actor]
  (last (clojure.string/split actor #"\_")))

(def answer
  (for [actor filtered-actors
       :let [rest-animals (group-by rest animals)]
       :when (get rest-animals (rest (get-surname actor)))]
   [actor (get rest-animals (rest (get-surname actor)))]))
