; https://www.npr.org/2021/08/01/1023212471/sunday-puzzle-human-body-parts

; Think of a common Britishism — a word that the British use that's not common 
; in the U.S. Write it in all capital letters. Turn it upside-down 
; (that is, rotate it 180 degrees). The result is a famous hero of books and 
; movies. Who is it?

(def fcs
  (->> "resources/fictional_characters_common.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(def b
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       distinct))

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(defn rotational-symmetry? [word]
  (re-find #"^[himnoswxzHIMNOSWXZ]+$" word))

(def result (filter rotational-symmetry? (mapv clean-up-word b)))

