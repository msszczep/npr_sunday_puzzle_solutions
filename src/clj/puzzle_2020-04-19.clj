; https://www.npr.org/2020/04/19/837909831/sunday-puzzle-all-about-u

; Name part of the human body in seven letters. The first four 
; letters, in order, spell a familiar boy's name. The second 
; through fifth letters, in order, also spell a familiar boy's name. 
; What body part is it?

(def words
  (->> "resources/body_parts.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace % #" " ""))
       (filter (comp (partial = 7) count))))

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Z]" "")))

(def seven-letter-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (filter (comp (partial = 7) count))))

(def names
  (->> "resources/male_first_names_lower.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 4) count))
       set))

(def answer
  (for [w seven-letter-words
       :when (and (contains? names (subs w 0 4))
                  (contains? names (subs w 1 5)))]
    w))



