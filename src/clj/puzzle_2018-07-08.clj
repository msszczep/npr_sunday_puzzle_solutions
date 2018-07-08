; https://www.npr.org/2018/07/08/626992499/sunday-puzzle-hot-hot-hot

; The word PANCAKE has an unusual property. If you remove its last 
; letter, you get a series of U.S. state postal abbreviations — PA, 
; NC, and AK. Can you name a major city and state that both have this 
; property? To solve this, first think of a state in which you can drop 
; its last letter to leave a series of state postal abbreviations. Then 
; find a major city in that state that also has this property. The city 
; and state names have to be different. What city and state is it?


(def us-postal-abbreviations
   #{"al" "ak" "az" "ar" "ca" "co" "ct" "de" "fl" "ga" "hi" "id" "il" "ia" "in" "ks"
     "ky" "la" "me" "md" "ma" "mi" "mn" "ms" "mo" "mt" "ne" "nv" "nh" "nj" "nm"
     "ny" "nc" "nd" "oh" "ok" "or" "pa" "ri" "sc" "sd" "tn" "tx" "ut" "vt" "va"
     "wa" "wv" "wi" "wy"})


(def us-states
   (->> "resources/us-states.txt"
        slurp
        clojure.string/split-lines
        (map clojure.string/lower-case)
        (map #(clojure.string/replace % #" " ""))
        set))


(defn word-ok? [w]
  (let [length-to-use (/ (dec (count w)) 2)]
   (->> w
        butlast
        (partition 2)
        (map (partial apply str))
        (filter us-postal-abbreviations)
        count
        (= length-to-use))))


(filter word-ok? us-states)


