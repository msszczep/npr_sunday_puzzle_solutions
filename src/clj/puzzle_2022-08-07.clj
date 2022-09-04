; https://www.npr.org/2022/08/07/1116218368/sunday-puzzle-cities-of-america

; This is the start of a two-week creative challenge. The object is to write a sentence 
; using only the letters of any particular U.S. state. You can pick the state and repeat 
; letters as often as necessary. For example:
; NEW YORK --> No one knew we were ornery.
; WASHINGTON --> Sighting a ghost tonight was astonishing.
; Entries will be judged on originality, sense, naturalness of syntax, humor, and overall 
; elegance. *No more than three sentences per entry, please.*

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(def us-states
  (->> "resources/us-states.txt"
       slurp
       clojure.string/split-lines))

(defn normalize-state [s]
  (-> s
      (clojure.string/replace #" " "")
      clojure.string/lower-case
      (clojure.string/replace #"[^a-z]" "")))

(def n-states (mapv normalize-state us-states))

(defn dedup [s]
  (apply str (sort (set s))))

(def states-letters-to-use
  (mapv (juxt identity dedup filter-words-by-state) n-states))

(defn filter-words-by-state [s]
  (sort (remove nil?
            (for [w cmu-words]
              (when (= [true] (distinct (map (partial contains? (set (map char s))) (map char w))))
                w)))))

(reverse (sort-by last (map (juxt first second (comp count last)) states-letters-to-use)))
"rhodeisland"
"landlord donald adored in shenandoah"
