; https://www.npr.org/2023/09/10/1198507860/sunday-puzzle-switch-it-out

; Name a creature that has a world capital in its name. Replace the capital 
; with another creature and you'll get another world capital. What is it?

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

(def world-capitals
  (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))
               (map last)
               (map clean-up-word)
               sort))

(def solution?
  (for [w world-capitals
        :let [f (filter #(re-find (re-pattern w) %) animals-to-use)]
        :when (not (empty? f))]
   [w f]))

