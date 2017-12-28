;; http://www.npr.org/2016/07/17/486308247/better-get-revved-up-for-a-puzzle-stuffed-with-vs-and-es

;; Name a prominent American politician — first and last names, 11 letters total.
;; Rearrange these letters, and you'll get a country plus the former name of another country.
;; Who's the politician, and what countries are these?

;; http://downloads.dbpedia.org/2015-04/core/

;; grep elements persondata_en.nt  | grep description | grep Amer | grep politic |
;; sed 's/\<http\:\/\/dbpedia.org\/resource\///' | sed 's/\>//g' | awk '{print $1}' > politicians.txt

(->> (slurp "/Users/msszczep1/Desktop/politicians.txt")
     clojure.string/split-lines
     (filter (comp (partial = 12) count))
;     count
     )
