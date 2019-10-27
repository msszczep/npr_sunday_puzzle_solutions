

; Think of an informal term for a beverage.  Say it in pig Latin and you'll
; get another informal term for a beverage.  What terms are these?

(def synsets
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(defn word-in-synset? [word synset]
  (contains? (set synset) word))

(def beverages
  (filter (partial word-in-synset? "drinking") synsets))

(defn find-ay [synset]
  (filter (partial re-find #"^.*ay$") synset))

; (remove empty? (map find-ay beverages))
