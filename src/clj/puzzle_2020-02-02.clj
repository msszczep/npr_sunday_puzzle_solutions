; https://www.npr.org/2020/02/02/801876739/sunday-puzzle-missing-doubles

; The actress Michael Learned, who played the mother on The Waltons, has 
; an unusual property in her name. The last three letters of her first 
; name are the same as the first three letters of her last name reversed. 
; The name of what current celebrity has the same property? Here's a hint: 
; The first and last names each have 6 letters.

(def actors
   (->> "resources/actors.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (remove (partial re-find #"\%"))))


(defn six-and-six? [a]
  (->> #"\_"
       (clojure.string/split a)
       (map count)
       (= [6 6])))


(defn three-char-reversed? [a]
  (let [split-name (clojure.string/split a #"\_")
        first-name (first split-name)
        last-name (last split-name)
        component-one (apply str (take-last 3 first-name))
        component-two (apply str (reverse (take 3 last-name)))]
    (= component-one component-two)))


(def solution
  (filter three-char-reversed? (filter six-and-six? actors)))



