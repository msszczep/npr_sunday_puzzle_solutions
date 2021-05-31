; https://www.npr.org/2021/05/23/999454616/sunday-puzzle-name-that-city

; Think of an eight-letter word in which the third and sixth letters are "A." 
; Remove the A's. The remaining six letters start a common series. What is it? 
; And what comes next in that series?

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(defn criteria? [w]
  (let [m (map char w)]
    (and (= 8 (count w))
         (= \a (nth m 2))
         (= \a (nth m 5)))))

(defn replace-36 [w]
  (let [[a b _ c d e _ f] (map char w)]
    (str a b c d e f)))

(clojure.pprint/pprint (mapv (juxt identity replace-36) (filter criteria? ospd)))

(def ospd
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines))

(def ni2
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (mapv clojure.string/lower-case)))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (mapv clojure.string/lower-case)))
