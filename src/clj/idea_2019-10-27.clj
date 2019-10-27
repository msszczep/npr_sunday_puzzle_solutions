; elbow -> below

(def words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"  "))
       (map first)
       set))


(defn sorted-in-dict? [w]
  (->> w
       sort
       (apply str)
       cmu-words
       nil?
       not))


(def solution
  (for [w cmu-words
        :let [sorted-w (apply str (sort w))]
        :when (and (sorted-in-dict? w)
                   (not= w sorted-w)
                   (> (count w) 5))]
    [w sorted-w]))
