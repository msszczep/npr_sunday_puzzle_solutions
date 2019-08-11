; https://www.npr.org/2019/08/11/750176148/sunday-puzzle-cut-and-paste

; Think of a common 5-letter word. If you insert an E after the second 
; letter, you'll get a common 6-letter word. If instead you insert an E 
; after the fourth letter, you'll get another 6-letter word. And if 
; instead you insert an E at the end, you'll get still another 6-letter 
; word. What words are these?

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-word-count [n] 
  (set (filter (comp (partial = n) count) words)))

(def fives (filter-word-count 5))
(def sixes (filter-word-count 6))


(defn transform-word [word split-point]
   (let [w (split-at split-point word)] 
     (->> w
          last
          (concat (first w) [\e])
          flatten
          (apply str))))


(def solution
  (for [five fives
        :when (and (sixes (transform-word five 2))
                   (sixes (transform-word five 4))
                   (sixes (transform-word five 5)))]
    [five
     (sixes (transform-word five 2))
     (sixes (transform-word five 4))
     (sixes (transform-word five 5))]))
