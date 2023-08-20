; https://www.npr.org/2023/08/20/1194416790/sunday-puzzle-you-better-make-it-last

; Name part of the human body above the neck in 9 letters. Rearrange them 
; to name another part of the human body found below the neck. Only some 
; people have the first body part. Everyone has the second one. What 
; parts of the human body are these?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def body-parts
  (->> "resources/body_parts.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

(def nines
  (filter (comp (partial = 9) count) body-parts))

(map val (group-by frequencies nines))


