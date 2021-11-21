; https://www.npr.org/2021/10/31/1050807894/sunday-puzzle-pay-a-t-tention-to-these-words

; Think of a popular tourist attraction in two words. The second, fourth, 
; and sixth letters of the second word, in order, spell the first name of a 
; famous author. The last four letters of the first word spell the author's 
; last name. Who is the author, and what is the tourist attraction?

(def who
  (->> "resources/who2.txt"
       slurp
       clojure.string/split-lines
       (mapv #(clojure.string/split % #", "))))

(def solution-set
  (->> who
       (filter (comp (partial = 2) count))
       (filter (comp (partial = 4) count first))
       (filter (comp (partial = 3) count last))))

(def files
  ["53265202.json" "53414533.json" "53556494.json" "53833455.json" "54608094.json"])

(defn split-invoice-entry [m]
  (update-in m [:chars] #(clojure.string/split % #"\#? ")))

(defn bigf [file]
  (let [d (-> "resources/bravotran_challenge/data/"
              (str file)
              slurp
              (json/read-str :key-fn keyword))
        r1 (first (filter (comp (partial re-find #"INVOICE") :chars) d))
        r2 (remove (comp (partial re-find #"INVOICE") :chars) d)
        r3 (if (nil? r1) r2 (concat r2 [(split-invoice-entry r1)]))]
    (json/write-str r3)))

(mapv bigf files)

