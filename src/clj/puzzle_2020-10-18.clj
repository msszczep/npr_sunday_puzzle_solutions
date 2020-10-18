; https://www.npr.org/2020/10/18/924911384/sunday-puzzle-p-b-and-j

; Name a world capital.  Change one letter in it to D-Y.  The result will be
; two words, one after the other.  The first word names somebody you like to
; be around.  The second names somebody you don't like to be around.  What is it?

(def world-capitals
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") second))
       sort))


