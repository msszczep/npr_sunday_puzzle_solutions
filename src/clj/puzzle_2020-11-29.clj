; https://www.npr.org/2020/11/29/939683206/sunday-puzzle-triple-threat

; Name an animal and spell it backward. Now name a variety of meat 
; and insert it inside the animal's name that you've spelled backward. 
; A common word will be revealed. What is it?

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(def animals
  (->> (slurp "resources/wordnet_data_cleaned.noun")
       clojure.string/split-lines
       (map #(clojure.string/split % #" "))
       (map (juxt #(nth % 4) second))
       (filter (comp (partial = "05") last))
       (map first)
       (filter (comp (partial > 8) count))
;       (mapv (juxt identity (comp (partial apply str) reverse)))
))

(defn get-splits [w]
  (mapv #(mapv (partial apply str) (split-at % w)) 
        (range 1 (count w))))

(defn check-first-part [word first-part]
  (when (<= (count first-part) (count word))
    (= first-part (subs word 0 (count first-part)))))

(defn check-second-part [word second-part]
  (when (<= (count second-part) (count word))
    (= second-part (subs word (- (count word) (count second-part))))))

;(mapv #(vector (check-first-part "abcdef" (first %)) (check-second-part "abcdef" (last %))) (get-splits "abclambdef"))

(defn match-splits-to-word? [word [first-part last-part]]
  (mapv ))


