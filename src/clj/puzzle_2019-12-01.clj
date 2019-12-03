;;  https://www.npr.org/2019/11/24/782295066/sunday-puzzle-made-up-phrases

;; Name something you find in a grocery store.  Two words.
;; Three letters in the first, six letters in the second. Switch the
;; third and seventh letters, and read the result backward.  The result
;; will name that same grocery item again.  What is it?


(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))


(def all-words
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/split % #" " 2))
       (map first)
       (filter (partial re-find #"^[A-Z]+$"))))

(def three-letter-words
  (filter (comp (partial = 3) count) all-words))

(def six-letter-words
  (filter (comp (partial = 6) count) all-words))

(def solution 
  (for [w1 three-letter-words
        w2 six-letter-words
        :let [l1 (map char w1)
              l2 (map char w2)]
        :when (and (= (nth l1 0) (nth l2 5))
                      (= (nth l1 1) (nth l2 4))
                      (= (nth l2 0) (nth l2 2)))]
    [w1 w2]))

; (take 100 (sort (set (map first solution))))

; (filter #(= (first %) "CAT") solution)
