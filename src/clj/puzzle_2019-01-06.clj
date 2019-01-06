; https://www.npr.org/2019/01/06/682575357/sunday-puzzle-stuck-in-the-middle

; Name a major U.S. city in 10 letters. If you have the right one, 
; you can rearrange its letters to get two 5-letter words that are 
; synonyms. What are they?

(def all-words
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       set))

(def us-cities
  (->> "resources/usa_cities.txt"
       slurp
       clojure.string/split-lines
       (map #(-> % (clojure.string/split #"\t") second))
       (map clojure.string/lower-case)
       (map #(clojure.string/replace % #" " ""))))

(def ten-letter-cities
  (filter (comp (partial = 10) count) us-cities))

(def five-letter-words
  (filter (comp (partial = 5) count) all-words))

(defn subanagram-generator [submitted-word]
  (let [submitted-word-freqs (merge 
                              (zipmap "abcdefghijklmnopqrstuvwxyz" (repeat 26 0))
                              (frequencies (clojure.string/lower-case submitted-word)))]
    (->> five-letter-words
         (filter (fn [word] 
                   (let [lc-word (clojure.string/lower-case word)]
                     (every? #(<= ((frequencies lc-word) %)
                                  (submitted-word-freqs %)) 
                       (set lc-word)))))
         sort)))

(defn do-they-fit? [ten-letter-word [w1 w2]]
  (= (frequencies ten-letter-word)
     (frequencies (str w1 w2))))

(defn find-solution [city]
  (->> city
       subanagram-generator
       (combinations 2)
       (filter (partial do-they-fit? city))))

(def solution 
  (->> ten-letter-cities
       ((juxt identity find-solution))))

; 1. filter five-letter subanagrams for a 10-letter city
; 2. pair them up
; 3. keep those those that combine successfully
; 4. see if any of those are synonyms
