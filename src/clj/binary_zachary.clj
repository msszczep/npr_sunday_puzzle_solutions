; Find all numbers for the number "mind-reading" trick.

(defn pad-zero [s n]
  (str (reduce str (repeat (- n (count s)) "0")) s))

(def list-of-numbers
  (let [max-number 128
        max-number-bin-count (count (Integer/toBinaryString max-number))]
   (map (juxt identity #(pad-zero (Integer/toBinaryString %)
                                  max-number-bin-count)) 
        (range max-number))))

(defn filter-by-digit [d n]
  (= \1 (nth (reverse n) (/ (Math/log d) (Math/log 2)))))

(defn get-answer [slot]
  (->> list-of-numbers
      (filter (comp (partial filter-by-digit slot) second))
      (map first)))
     

; (get-answer 4)
