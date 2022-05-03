; https://www.npr.org/2022/05/01/1095762239/sunday-puzzle-tucked-away-in-the-capital

; Write down the name of a number. Move each letter four spots later 
; in the alphabet — so A would become E, B would become F, etc. The 
; result will be a number that's 44 more than your first one. 
; What numbers are these?

(def letters "abcdefghijklmnopqrstuvwxyz")

(def moved-letters "efghijklmnopqrstuvwxyzabcd")

(def move-five (zipmap letters moved-letters))

; a e, e i, k o, q u, w a

(def nums-to-use 
  ["one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"
   "eleven" "twelve" "thirteen" "fourteen" "fifteen" "sixteen" "seventeen"
   "eighteen" "nineteen" "twenty" "twentyone" "thirty" "forty" "fifty" 
   "sixty" "seventy" "eighty" "ninety" "onehundred" "thousand"
   "hundred" "million" "billion" "zero" "negativeone"])

(defn transform-string [s] 
  (apply str (map move-five s)))

(def espanol
  ["uno" "dos" "tres" "quatro" "cinco" "seis" "siete" "ocho" "nueve" "diez" "once"])

(def solution
   (mapv (juxt identity transform-string) nums-to-use))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)
       set))

(def cmu-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       distinct
       set))

 (def s2
   (filter (fn [[a b]] (and (ospd a)
                            (ospd b))) 
      (mapv (juxt identity transform-string) ospd)))

 (def s3
   (filter (fn [[a b]] (and (cmu-words a)
                            (cmu-words b))) 
      (mapv (juxt identity transform-string) cmu-words)))

 (def s4
   (filter (fn [[a b]] (and (ni2 a)
                            (ni2 b))) 
      (mapv (juxt identity transform-string) ni2)))


