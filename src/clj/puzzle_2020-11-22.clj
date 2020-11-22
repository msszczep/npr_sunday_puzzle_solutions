; https://www.npr.org/2020/11/22/

; Name a marine animal in two words.  Remove two consecutive letters
; in the name and read the resulting string of letters in order from
; left to right.  You'll name a major American city.  What is it?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def animals
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "05") last))
       (map first)
       (filter (comp (partial re-find #"\_")))
       (filter (comp (partial = 2) count #(clojure.string/split % #"_")))))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))
       (map clojure.string/lower-case)))

; remove non-letters from cities
; take animal
; transform animal: remove non-letters, remove two consecutive letters
; filter on cities

(defn remove-two-consec [word number-set]
  (->> word
       (map-indexed vector)
       (remove (comp (partial contains? number-set) first))
       (mapv last)
       (apply str)))

(defn map-two-consec [animal]
  (mapv (partial remove-two-consec animal)
        (mapv set (partition 2 1 (range (count animal))))))

(def sol
  (mapv map-two-consec (map clean-up-word animals)))
