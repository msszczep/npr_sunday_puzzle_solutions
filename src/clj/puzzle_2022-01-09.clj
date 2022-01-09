; https://www.npr.org/2022/01/09/1071581311/sunday-puzzle-movie-shmovie

; Let A = 1, B = 2, C = 3, etc. Think of a five-letter word whose letters' 
; values add up to 51. Now take this word's last two letters. Add their 
; values. (For example A and C would total 4.) Change these two letters to 
; the single letter of the alphabet that represents their total. In this 
;  case, D. The result will be a new word that is the opposite of the original. 
; What words are these? 

(def ref-map 
  (zipmap "abcdefghijklmnopqrstuvwxyz" (range 1 27)))

(defn sum-standard-value [s]
  (->> s
       clojure.string/lower-case
       (map ref-map)
       (remove nil?)
       (apply +)))

(def words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines))

(def candidates 
  (->> words
       (filter (comp (partial = 5) count))
       (filter (comp (partial = 51) sum-standard-value))))
