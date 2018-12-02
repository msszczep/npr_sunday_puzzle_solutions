; https://www.npr.org/2018/11/11/666376065/sunday-puzzle-lets-get-phonetical

; Think of a familiar four-word phrase that means "to be last." 
; Together the first two words are a synonym for the last word. 
; What phrase is it?

(def phrases
  (->> "resources/proverbs2.txt"
        slurp
        clojure.string/split-lines))

(defn get-four-word-phrases [phrase]
  (-> phrase
      (clojure.string/split #" ")
      count
      (= 4)))

; (filter get-four-word-phrases phrases)
