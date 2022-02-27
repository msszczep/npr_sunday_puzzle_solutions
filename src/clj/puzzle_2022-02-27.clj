; https://www.npr.org/2022/02/27/1083032736/sunday-puzzle-opposites-attract

; Name a famous actor — first and last names. Remove the last letter of each name. 
; You'll be left with an animal and an adjective that describes that animal, 
; respectively. Who is the actor?

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(defn my-clean-up [w]
  (->> w
       clojure.string/lower-case
       butlast
       (apply str)))

(defn in-ospd? [[w1 w2]]
  (let [c1 (my-clean-up w1)
        c2 (my-clean-up w2)]
    (and (ospd c1)
         (ospd c2))))

(def solution
  (->> who
       (filter (comp (partial = 2) count))
       (filter in-ospd?)))









