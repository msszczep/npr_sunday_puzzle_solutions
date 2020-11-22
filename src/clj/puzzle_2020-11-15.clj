; Name a title character from books and TV (5, 5).  you can rearrange the letters
; to get two words describing what you can hear and do in church.  What character
; is it?

(def fcs
  (->> "resources/fictional_characters_common.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(defn five-five? [t]
  (->> #" "
       (clojure.string/split t)
       (mapv count)
       (= [5 5])))

