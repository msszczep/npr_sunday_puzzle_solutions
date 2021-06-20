; https://www.npr.org/2021/06/06/1003633804/sunday-puzzle-television-scramble

; Write down the name of a country plus its capital, one after the other. Hidden 
; in consecutive letters inside this is the name of a film that won an Academy Award 
; for Best Picture. Name the country, capital, and film.

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(def world-capitals
  (->> "resources/world-capitals.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)
       sort))


