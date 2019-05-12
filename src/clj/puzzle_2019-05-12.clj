; https://www.npr.org/2019/05/12/722484920/sunday-puzzle-clues-come-in-2s

; Think of a 6-letter conveyance on wheels. Drop the first letter. 
; Add a new letter at the end. The result will be another 6-letter 
; conveyance on wheels. What conveyances are these?

(def scrabble-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 6) count))
       set))

(def all-cmu-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^A-Z]" ""))
        (filter (comp (partial = 6) count))
        set))

(defn get-conveyances [s]
  (let [new-s (apply str (rest s))]
    (re-pattern (str "^" new-s "[A-Z]$"))))


(defn get-words-from-word [w]
  (->> all-cmu-words
       (map (partial re-find (get-conveyances w)))
       (remove nil?)))

(def solution
 (->> all-cmu-words
      (map (juxt identity get-words-from-word))
      (filter (comp not empty? last))))
