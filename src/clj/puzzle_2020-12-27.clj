; https://www.npr.org/2020/12/27/950036966/sunday-puzzle-word-sandwiches

; Think of a familiar two-word phrase (5, 2). Replace the last letter 
; with the next letter of the alphabet. The result will be a palindrome 
; (the seven letters will read backward and forward the same). What phrase is it?

(def moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))
       flatten
       distinct))

(defn palindrome-modified? [s]
  (let [[a b c d e _ f g] (mapv char s)] 
    (and (= b f)
         (= c e))))

(def solution?
  (->> moby
       (filter (partial re-find #"^[a-z]{5}\s[a-z]{2}$"))
       (filter palindrome-modified?)))


