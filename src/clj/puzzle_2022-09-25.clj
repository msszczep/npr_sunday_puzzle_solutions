; https://www.npr.org/2022/09/25/1124931137/sunday-puzzle-find-the-sport

; Take the name of a large financial corporation in 10 letters. Drop the 
; fourth and fifth letters. Move the sixth and seventh letters fo the front. 
; You'll name a person associated with financial misdeeds. What is the 
; company, and who is the person?

(defn rearrange [n]
  (let [[a b c d e f g h i j] (map char n)]
    (apply str [f g a b c h i j])))




