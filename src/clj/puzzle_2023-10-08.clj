; https://www.npr.org/2023/10/08/1204456271/sunday-puzzle-a-d-arn-h-ard-puzzle

; Think of a mammal, an insect, and a bird, in that order — six, three, 
; and four letters, respectively. Say them out loud and you'll name 
; something often seen around this time of year. What is it?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def animals-to-use
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "05") last))
       (map first)
       (filter (comp (partial < 2) count))
       (mapv clean-up-word)
       distinct))

(sort (frequencies (map count animals-to-use)))

(sort (filter (comp (partial = 3) count) animals-to-use))
