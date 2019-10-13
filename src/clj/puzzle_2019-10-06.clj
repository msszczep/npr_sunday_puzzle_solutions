; https://www.npr.org/2019/10/06/767281654/sunday-puzzle-5-to-7

; There are two answers to this one, and you have to get them both. 
; Name two tasty things to eat, each in 8 letters, in which the only 
; consonant letters are L and P.

(defn word-with-lp [word]
  (let [f (frequencies word)]
    (and (not (nil? (get f \l)))
         (not (nil? (get f \p)))
         (nil? (get f \b))
         (nil? (get f \c))
         (nil? (get f \d))
         (nil? (get f \f))
         (nil? (get f \g))
         (nil? (get f \h))
         (nil? (get f \j))
         (nil? (get f \k))
         (nil? (get f \m))
         (nil? (get f \n))
         (nil? (get f \q))
         (nil? (get f \r))
         (nil? (get f \s))
         (nil? (get f \t))
         (nil? (get f \v))
         (nil? (get f \w))
         (nil? (get f \x))
         (nil? (get f \z)))))


(def words-to-use
  (->> "resources/ospd3.txt"
       slurp
       clojure.string/split-lines
       (filter (comp (partial = 8) count))
       (filter word-with-lp)))
