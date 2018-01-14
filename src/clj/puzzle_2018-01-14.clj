; https://www.npr.org/2018/01/14/577363065/sunday-puzzle-a-twisted-ending

; Name a world capital. It's an older way of spelling the name. 
; Drop three letters, and the remaining letters, in order, will 
; name another world capital. Both cities have more than a 
; million residents. What cities are these?

(def world-capitals 
  (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> %
                 (clojure.string/split #",")
                 last
                 (clojure.string/replace #" " "")
                 (clojure.string/replace #"’" "")
                 (clojure.string/replace #"\." "")
                 (clojure.string/replace #"-" "")
                 (clojure.string/replace #"í" "i")
                 (clojure.string/replace #"é" "e")
                 (clojure.string/replace #"ã" "a")
                 (clojure.string/replace #"á" "a")
                 clojure.string/lower-case))))


(defn is-subanagram? [world-capital-base world-capital-compare]
  (let [world-capital-base-frequencies
         (merge 
           (zipmap "abcdefghijklmnopqrstuvwxyz" (repeat 26 0))
           (frequencies world-capital-base))
        world-capital-compare-frequencies 
         (frequencies world-capital-compare)]
    (every? #(<= (world-capital-compare-frequencies %)
                 (world-capital-base-frequencies %)) 
            (keys world-capital-compare-frequencies))))


(def results
  (for [x world-capitals
        y world-capitals
        :when (and (is-subanagram? x y)
                   (not= x y)
                   (= 3 (- (count x) (count y))))]
    [x y]))
