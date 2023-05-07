; https://www.npr.org/2023/05/07/1174544511/sunday-puzzle-seeing-double
; Think of part of the human body whose name is a compound word like 
; fingertip or toenail.  Add an N and rearrange the result to get another 
; part of the body whose name is also a compound word. What body parts are these?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def body-parts
  (->> "resources/body_parts.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

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

(def answer
 (for [b body-parts
       :let [f (filter (fn [x] (and (= (count x) (dec (count b)))
                                    (subanagram? b x))) body-parts)]
       :when (and (not (empty? f)))]
   [b f]))

