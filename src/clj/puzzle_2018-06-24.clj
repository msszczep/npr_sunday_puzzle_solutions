; https://www.npr.org/2018/06/24/622526260/sunday-puzzle-just-so

; Think of a well-known commercial name in 9 letters. Change 
; both the fourth and ninth letters to X's and you'll get two 
; other familiar commercial names, one after the other. 
; What names are these?

(def all-cmu-words
   (->> "resources/cmudict-0.7b.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^A-Z]" ""))))


(def all-ni2-words
   (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (filter (partial re-find #"^[a-z]{9}$"))
        (map clojure.string/upper-case)
        set))


(def nine-letter-names
   (set (filter (comp (partial = 9) count) all-cmu-words)))

(def names-with-x
   (->> all-cmu-words
        (filter (partial re-find #"X$"))))

(def c9
  (clojure.set/difference nine-letter-names all-ni2-words))


(filter (partial re-find #"IMA[A-Z]$") nine-letter-names)

(filter (partial re-find #"^ROLE[A-Z]") nine-letter-names)

(filter (partial re-find #"ROLE[A-Z]$") nine-letter-names)
