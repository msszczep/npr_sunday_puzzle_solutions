; https://www.npr.org/2020/03/29/823130995/sunday-puzzle-silent-anagrams

; Here's an April Foolish puzzle. Think of a world capital. Drop the 
; third and fourth letters, and keeping the remaining letters in order 
; you'll name a state. What state is it?


(def world-capitals
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") second))))


(def us-states
  (->> "resources/us-states.txt"
       slurp
       clojure.string/split-lines))


(defn normalize [s]
  (-> s
      (clojure.string/replace #" " "")
      clojure.string/lower-case
      (clojure.string/replace #"[^a-z]" "")))


(def proposed-answer
  (let [states (map normalize us-states)
        caps (map normalize world-capitals)]
    (for [cap caps
          state states
          :let [truncated-cap (str (subs cap 0 2) (subs cap 4))]
          :when (= state truncated-cap)]
      [cap truncated-cap state])))

(clojure.pprint/pprint 
 (sort (map (juxt identity (fn [cap] (str (subs cap 0 2) (subs cap 4)))) 
        (map normalize world-capitals))))
