; https://www.npr.org/2020/07/05/887263368/sunday-puzzle-try-t-h-is

; Think of an eight-letter word for something we all crave now. 
; It consists of three consecutive men's nicknames. What are they?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Z]" "")))

(def eight-letter-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (filter (comp (partial = 8) count))))

(def eight-letter-words-2
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 8) count))))

(def names
  (->> "resources/male_first_names_lower.txt"
       slurp
       clojure.string/split-lines
       set))

(defn is-substring? [s sub?]
  (contains? (set (map (partial apply str) 
                       (partition (count sub?) 1 s))) 
             sub?))

(def answer
  (for [w eight-letter-words-2
       :let [ns (filter (partial is-substring? w) names)]
       :when (= 3 (count ns))]
    [w ns]))
