; https://www.npr.org/2020/09/13/912278787/sunday-puzzle-dont-delete

; Name a famous person with the initials M.C. The first initial and last name
; anagram to the person's field of renown.  What is it?

(defn clean-up-word [word]
  (clojure.string/lower-case (clojure.string/replace word #"[^A-Z]" "")))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))))

(def who2
   (->> "resources/who2.txt"
        slurp
        clojure.string/split-lines
        (mapv clojure.string/lower-case)))

(defn has-comma? [n]
  (not (nil? (re-find #"\," n))))

(defn is-mc? [n]
  (let [[lname fname] (mapv clojure.string/trim 
                            (clojure.string/split n #","))
        f-initial (first fname)
        l-initial (first lname)]
    (and (= \m f-initial)
         (= \c l-initial))))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(def mcs 
  (->> who2
       (filter has-comma?)
       (filter is-mc?)))

(defn get-last-name [n]
  (->> #","
       (clojure.string/split n)
       (mapv clojure.string/trim)
       first))

(def solution
  (for [m mcs
        :when (->> cmuwords
                   (filter (partial anagram? (str \m (get-last-name m))))
                   empty?
                   not)]
        [m (filter (partial anagram? (str \m (get-last-name m))) cmuwords)]))
