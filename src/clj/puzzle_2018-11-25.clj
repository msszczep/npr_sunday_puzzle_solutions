; https://www.npr.org/2018/11/25/670596165/sunday-puzzle-the-big-mo

; Think of a well-known food brand. Add the letters W-O-W. 
; Then rearrange the result to name another well-known food brand. 
; What is it?

; Newman's Own

; nemansn

; nam nens
; sam 
; sennamn
; mensann
; mens ann
; senn man
; names mn

; nam esnn

(def words 
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(-> %
                 (clojure.string/split #"  " 2)
                 first
;                 clojure.string/lower-case
;                 (clojure.string/replace #"[^a-z]" "")
                 ))))

(defn wow? [w]
  (let [f (frequencies w)]
    (and (> (get f \O 0) 0) 
         (> (get f \W 0) 1))))

(defn wow2? [w]
  (let [f (frequencies w)
        g (frequencies "NEMANSN")]
    (= f g)))

(defn wow3? [w]
  (let [f {\N 3, \E 1, \M 1, \A 1, \S 1}]
    ))

(defn wow4? [word]
  (every? #(<= (get (frequencies word) % 0)
               (get {\N 3, \E 1, \M 1, \A 1, \S 1} % 0))
    (set word)))

; enn

(def wow
  (filter wow? words))

(def wow2
  (filter wow2? words))

(def wow4
  (filter wow4? words))
