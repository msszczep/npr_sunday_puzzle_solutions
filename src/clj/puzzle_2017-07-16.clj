;; http://www.npr.org/2017/07/16/537225382/sunday-puzzle-wehn-wrods-get-rearearngd

;; Name a U.S. city and its state — 12 letters altogether. Change two letters in 
;; the state's name. The result will be the two-word title of a classic novel. 
;; What is it?

(->> "usa_cities.txt"
     clojure.java.io/resource
     clojure.java.io/reader
     line-seq
     ;; (map #(re-find #"^.+?\t(.+?)\t(.+?)\t.*" %))
     (map #(-> %
               (clojure.string/split #"\t")
               rest
               (->> (take 2)
                    (apply str))
               (clojure.string/replace #" " "")
               clojure.string/lower-case))
     (filter (comp (partial = 12) count))
     sort)
