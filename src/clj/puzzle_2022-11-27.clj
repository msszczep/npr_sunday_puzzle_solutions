;; https://www.npr.org/2022/11/27/1139275402/sunday-puzzle-jokes-on-you

;; What common eight-letter noun can be shortened in two ways — using 
;; either its first three letters or its last four letters? The answer 
;; is a familiar item.

(def answer
 (->>
   (slurp "resources/wordnet_data_cleaned.noun")
   ;; Wordnet list of nouns
   clojure.string/split-lines ;; split lines
   (map #(clojure.string/split % #" "))
   (map (juxt #(nth % 4) second))     ;; get lexicographer contents
   (filter (comp (partial = "06") last)) ;; get human-made objects
   (filter (comp (partial = 8) count first)) ;; get list of 11-letter words
   (remove (comp (partial re-find #"\_") first)) ;; remove entries with underscores
   (map first)
   (map (juxt identity #(subs % 0 3) #(subs % 4 8)))))             ;; get substrings (can't use partial?)





