; https://www.npr.org/2021/05/30/1001564473/sunday-puzzle-double-double

; Name a famous city in 10 letters that contains an "S." Drop the "S." 
; Then assign the remaining nine letters their standard value in the 
; alphabet — A = 1, B= 2, C = 3, etc. The total value of the nine letters 
; is only 25. What city is it?

(defn sum-standard-value [s]
  (let [ref (zipmap "abcdefghijklmnopqrstuvwxyz" (range 1 27))]
    (->> s
         clojure.string/lower-case
         (map ref)
         (remove nil?)
         (apply +))))

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^a-zA-Z]" "")))

(def cities
  (->> "resources/us_cities_big_list.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

; time egrep '^[a-zA-Z_]{10,13}$' all_en_wikipedia_titles_20210420.txt > candidates.txt
; time egrep '[sS]' candidates.txt > candidates2.txt

(def all-w
  (->> "resources/candidates2.txt"
       slurp
       clojure.string/split-lines
       (mapv clean-up-word)))

(defn remove-s [word]
  (clojure.string/replace word #"[sS]" ""))

(def all-w-filtered
  (->> all-w
       (filter (comp (partial = 10) count))
       (filter #(re-find #"s" %))
       (filter (comp (partial = 25) sum-standard-value remove-s))))
