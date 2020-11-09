; https://www.npr.org/2020/10/04/919692327/sunday-puzzle-game-of-words

; What common seven-letter verb is made up of three consecutive musical
; notes in order?

(def musical-notes #{"do" "re" "ray" "mi" "me" "fa" "far" "so" "la" "ti"})

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(defn has-three-musical-notes? [w]
  (->> w
       (partition 2 1)
       (map (partial apply str))
       (filter musical-notes)
       count
       (= 3)))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 7) count))
       (filter has-three-musical-notes?)))

(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 7) count))
       (filter has-three-musical-notes?)))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (mapv clojure.string/lower-case)
       (filter (comp (partial = 7) count))
       (filter has-three-musical-notes?)))
