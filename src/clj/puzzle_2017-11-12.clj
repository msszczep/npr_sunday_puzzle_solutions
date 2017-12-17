; LINK

; Take name of a us state capital + name of capital a foreign country 
; us state will be embedded in consecutive letters.

(defn get-file-data [file]
  (->> (str "/Users/msszczep1/Scripts/npr_sunday_puzzle_solutions/resources/" file)
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))

(def us-data
  (get-file-data "us-state-capitals.txt"))

(def state-capitals (map (comp #(clojure.string/replace % #" " "") clojure.string/lower-case last) us-data))

(def us-states (set (map (comp #(clojure.string/replace % #" " "") clojure.string/lower-case first) us-data)))

(def world-data (get-file-data "world-capitals.txt"))

(def world-capitals (map (comp #(clojure.string/replace % #"[ \'\-’]" "") clojure.string/lower-case last) world-data))

(def result 
 (for [state-cap state-capitals
       world-cap world-capitals
       us-state (->> us-states 
                     (remove #(= % "oklahoma"))
                     (remove #(= % "washington"))
                     (remove #(= % "indiana")))
       :let [joined-val (str state-cap world-cap)]
       :when (re-find (re-pattern us-state) joined-val)]
   [joined-val us-state]
   ))
