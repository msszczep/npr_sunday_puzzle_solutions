; https://www.npr.org/2020/01/12/795520308/sunday-puzzle-short-as

; Think of a familiar three-word phrase that has the following property: The 
; first word is a number. Let X be that number. Then the last X letters of the 
; second word form, in order, a common abbreviation for the third word.

(def all-in-moby
  (->> "resources/moby_synonyms.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #","))))


(def roget
  (->> "resources/roget_clean.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #"=> "))
       (map last)
       (map #(clojure.string/split % #"\|"))))


(defn first-word-is-number? [phrase]
  (-> phrase
      (clojure.string/split #" ")
      first
      (clojure.string/lower-case)
      #{"one" "two" "three" "four" "five" "six" "seven" "eight" "nine" "ten"}
      nil?
      not))


(defn three-words? [s]
  (let [f (frequencies s)]
    (= 2 (get f \space 0))))


(def solution-set
  (->> roget
       (apply concat)
       set
       (filter three-words?)
       (filter first-word-is-number?)
))


