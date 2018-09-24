; https://www.npr.org/2018/09/23/650425268/sunday-puzzle-make-me-a-star

; Think of an affliction in five letters. Shift each letter three 
; spaces later in the alphabet — for example, A would become D, 
; B would become E, etc. The result will be a prominent name 
; in the Bible. Who is it?

(def potential-bible-names
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^A-Z]" ""))
        (filter (comp (partial = 5) count))
        set))

(def potential-bible-names2
   (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (filter (partial re-find #"^[A-Z]"))
        (map clojure.string/upper-case)
        (filter (comp (partial = 5) count))
        set
        ))


(def potential-maladies
   (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines
        (filter (partial re-find #"^[a-z]{5}$"))
        (map clojure.string/upper-case)))


(defn shift-letters [word]
  (let [shifted-letters {\A \D, \B \E, \C \F, \D \G, \E \H, 
                         \F \I, \G \J, \H \K, \I \L, \J \M
                         \K \N, \L \O, \M \P, \N \Q, \O \R
                         \P \S, \Q \T, \R \U, \S \V, \T \W
                         \U \X, \V \Y, \W \Z, \X \A, \Y \B, \Z \C}]
   (apply str (map shifted-letters word))))


(defn unshift-letters [word]
  (let [shifted-letters {\A \X, \B \Y, \C \Z, \D \A, \E \B, \F \C, \G \D, \H \E, \I \F, \J \G, \K \H, \L \I, \M \J, \N \K, \O \L, \P \M, \Q \N, \R \O, \S \P, \T \Q, \U \R, \V \S, \W \T, \X \U, \Y \V, \Z \W}]
   (apply str (map shifted-letters word))))


(def puzzle-solution
  (->> potential-maladies
       (map (juxt identity shift-letters))
       (filter (comp potential-bible-names2 last))
       ))

