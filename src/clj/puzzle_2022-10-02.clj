


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

(def ospd-words8
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (comp (partial = 8) count))
        set))

(def eights 
  (->> cmu-words
       (filter (comp (partial = 8) count))
       (remove ospd-words8)))

(def entries 
  (->> eights
       (group-by #(subs % 0 6))
       (filter (comp (partial < 1) count second))
       sort))

