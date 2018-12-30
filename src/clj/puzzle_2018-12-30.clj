; https://www.npr.org/2018/12/30/680701656/sunday-puzzle-new-names-in-the-news-2018

; What world capital becomes the informal name for a farm animal 
; if you change its third letter?

(def all-cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #" ") first))
       (map #(clojure.string/replace % #"[^A-Z]" ""))
       (map clojure.string/lower-case)
       set))


(def world-capitals
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #",") second))
       (map #(clojure.string/replace % #"[^A-Za-z]" ""))
       (map clojure.string/lower-case)))


(defn get-all-transformations [s]
  (let [third-letter (nth (map char s) 2)
        alphabet-to-use (remove (set (vector third-letter))
          (map char "abcdefghijklmnopqrstuvwxyz"))]
   (for [a alphabet-to-use]
     (str (subs s 0 2) (str a) (subs s 3)))))


(def solution
  (->> world-capitals
       (map (juxt identity (comp (partial filter all-cmu-words) get-all-transformations)))
       (remove (comp empty? second))))
