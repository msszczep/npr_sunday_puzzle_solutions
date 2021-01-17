; https://www.npr.org/2021/01/17/957639050/sunday-puzzle-switch-the-vowel 

; Name a national landmark (6,3). Add the name of a chemical element. 
; Rearrange all the letters to name two states. What are they?

(def us-states
  (->> "resources/us-states.txt"
       slurp
       clojure.string/split-lines))

(defn normalize [s]
  (-> s
      (clojure.string/replace #" " "")
      clojure.string/lower-case
      (clojure.string/replace #"[^a-z]" "")))

(def n-states (mapv normalize us-states))

(defn subanagram? [base compare]
  (let [base-frequencies
         (merge 
           (zipmap "abcdefghijklmnopqrstuvwxyz" (repeat 26 0))
           (frequencies base))
        compare-frequencies 
         (frequencies compare)]
    (every? #(<= (compare-frequencies %)
                 (base-frequencies %)) 
            (keys compare-frequencies))))

(def state-pairs (combinations 2 n-states))

(defn hoover-dam? [states]
  (let [c (str (first states) (last states))]
    (subanagram? c "hooverdam")))

(filter hoover-dam? state-pairs)
