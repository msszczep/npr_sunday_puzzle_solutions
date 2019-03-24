; https://www.npr.org/2019/03/10/701952153/sunday-puzzle-solve-this-case

; Think of a well-known brand name in 8 letters starting with H. 
; Change the H to an M and drop the last letter. You'll get another 
; well-known brand name in 7 letters. What commercial names are these?

(def all-cmu-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))))


(defn get-words [n regex]
  (->> all-cmu-words
       (filter (comp (partial = n) count))
       (filter (partial re-find regex))))


(def h-words (get-words 8 #"^H"))

(def m-words (set (get-words 7 #"^M")))

(def solution
  (for [h h-words
        :let [h-subset (str "M" (subs (subs h 1) 0 6))]
        :when (m-words h-subset)]
    [h h-subset]))

