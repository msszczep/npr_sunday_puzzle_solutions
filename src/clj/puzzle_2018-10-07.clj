; https://www.npr.org/2018/10/07

; Think of a title for a particular person -- two words, 15 letters
; in total -- in which the only vowel is "i".  What is it?

(def i-words
   (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (filter (partial re-find #"[iI]"))
        (remove (partial re-find #"[aAeEoOuUyY]"))
        (group-by count)
        ))

; Miss Mississippi
