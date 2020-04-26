; Create the shortest possible word ladder connecting LARGE to SMALL,
; changing one letter at a time, making a common uncapitalized word 
; each step of the way.  Here's the tricky part: Plurals and verbs
; formed by adding -s are not allowed.

(def words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 5) count))))

(defn one-char-off? [w1 w2]
  (->> w1
       (interleave w2)
       (partition 2)
       (remove (comp (partial = 1) count set))
       count
       (= 1)))

(defn find-nearest-word [start-word]
  (filter (partial one-char-off? start-word) words))


