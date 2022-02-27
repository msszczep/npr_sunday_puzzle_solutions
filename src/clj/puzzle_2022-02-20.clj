; https://www.npr.org/2022/02/20/1081951257/sunday-puzzle-thats-just-capital

; Name a part of the human body. Insert the name of another part 
; of the human body. You'll get a brand name found at the supermarket. What is it?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def body-parts
  (->> "resources/body_parts.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

(defn insert-word-into-word [w1 w2]
  (->> w1
       count
       range
       rest
       (mapv #(let [[s1 s2] 
                (split-at % w1)] 
                  (apply str (flatten [s1 (map char w2) s2]))))))

(def answer?
  (for [b1 body-parts
        b2 body-parts
        :let [wiw (insert-word-into-word b1 b2)
              w-result (mapv cmu-words wiw)]
        :when (and (not= b1 b2)
                   (not (every? nil? w-result)))]
    [b1 b2 w-result]))

