
(def postal-abbreviations 
  #{"AL" "AR" "AZ" "CA" "CO" "CT" "DE" "FL" "GA" "IA" "ID" 
    "IL" "IN" "KS" "KY" "LA" "MA" "MD" "ME" "MI" "MN" "MO" 
    "MS" "MT" "NC" "ND" "NE" "NH" "NJ" "NM" "NV" "NY" "OH" 
    "OK" "OR" "PA" "RI" "SC" "SD" "TN" "TX" "UT" "VA" "VT" 
    "WA" "WI" "WV" "WY"})

(def country-data
  (->> "resources/world-capitals.txt"
               slurp
               clojure.string/split-lines
               (map #(clojure.string/split % #","))))

(def countries
  (->> country-data
       (mapv (comp clojure.string/upper-case first))
       sort
       distinct))

(defn get-pa-by-country [country]
  (->> country
       (partition 2)
       (map (partial apply str))
       (filter postal-abbreviations)))

(->> countries
     (mapv (juxt identity get-pa-by-country (comp count get-pa-by-country)))
     (sort-by last)
     reverse
     (take 10))
