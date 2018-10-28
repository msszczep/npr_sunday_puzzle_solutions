; https://www.npr.org/2018/10/28/660936138/sunday-puzzle-row-row-row

; Think of a famous Broadway musical in two words. Change one 
; letter in it to the preceding letter of the alphabet — so B 
; would become A, C would become B, etc. Remove the space so 
; you have a solid word. The result will name something that 
; all of us are part of. What is it?

(defn has-one-space? [musical]
  (-> musical
      (clojure.string/split #" ")
      count
      (= 2)))


(def musicals
  (->> "resources/musicals.txt"
        slurp
        clojure.string/split-lines
        (filter has-one-space?)
        set))

(def words
  (->> "resources/ni2.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        set))

(def goback
  (zipmap
    (map char "bcdefghijklmnopqrstuvwxyz")
    (map char "abcdefghijklmnopqrstuvwxyz")))


(defn change-one-let [musical n]
  (apply str 
    (for [i (range (count musical))]
      (if (= i n)
        (goback (nth musical n))
        (nth musical i)))))


(defn transform-musical [musical]
  (let [m-temp (-> musical
                   clojure.string/lower-case
                   (clojure.string/replace #" " "")
                   (clojure.string/replace #"[^a-z]" ""))]
   (-> m-temp
       count
       range
       ((partial map (partial change-one-let m-temp))))))


(def answer
  (->> musicals
       (map transform-musical)
       flatten
       (filter words)))


(spit "flubber11.txt"
  (->> musicals 
       (filter (comp (partial > 12) count))
       (map (juxt identity transform-musical))
       sort))



