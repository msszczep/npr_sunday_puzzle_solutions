; https://www.npr.org/2023/03/05/1161192410/sunday-puzzle-in-reverse

; Name something scary in two words. Five of the letters are vowels, 
; which are all the same. And the consonants are all Roman numerals. 
; What scary thing is this? 

(def words
  (->> "resources/ospd3.txt"
        slurp
        clojure.string/split-lines))

(defn filter-function [w]
  (let [consonants #{\b \c \d \f \g \h \j \k \l \m 
                     \n \p \q \r \s \t \v \w \x \y \z}
        roman-numerals #{\c \d \m \v \l \x}
        vowels #{\a \e \i \o \u}]
    (and 
      (= (filter consonants w)
         (filter roman-numerals w))
      (= 1 (count (set (filter vowels w)))))))

 (filter filter-function words)
