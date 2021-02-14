; https://www.npr.org/2021/01/31/962412357/sunday-puzzle-game-of-words

; Starting in Montana, you can drive into South Dakota and then into Iowa. 
; Those three states have the postal abbreviations MT, SD, and IA — whose 
; letters can be rearranged to spell AMIDST. The challenge is to do this with 
; four connected states to make an eight-letter word. That is, start in a certain 
; state, drive to another, then another, and then another. Take the postal 
; abbreviations of the four states you visit, mix the letters up, and use them to 
; spell a common eight-letter word. Derrick and I know of only one answer. Can you do this?

; https://thefactfile.org/u-s-states-and-their-border-states/

(def bordering-states
  #{#{"AL" "MS"}
    #{"AL" "TN"}
    #{"AL" "FL"}    #{"AL" "GA"}
    #{"AZ" "NV"}
    #{"AZ" "NM"}
    #{"AZ" "UT"}
    #{"AZ" "CA"}
    #{"AZ" "CO"}
    #{"AR" "OK"}
    #{"AR" "TN"}
    #{"AR" "TX"}
    #{"AR" "LA"}
    #{"AR" "MS"}
    #{"AR" "MO"}
    #{"CA" "OR"}
    #{"CA" "NV"}
    #{"CO" "NM"}
    #{"CO" "OK"}
    #{"CO" "UT"}
    #{"CO" "WY"}
    #{"CO" "KS"}
    #{"CO" "NE"}
    #{"CT" "NY"}
    #{"CT" "RI"}
    #{"CT" "MA"}
    #{"DE" "NJ"}
    #{"DE" "PA"}
    #{"DE" "MD"}
    #{"FL" "GA"}
    #{"GA" "NC"}
    #{"GA" "SC"}
    #{"GA" "TN"}
    #{"ID" "UT"}
    #{"ID" "WA"}
    #{"ID" "WY"}
    #{"ID" "MT"}    
    #{"ID" "NV"}
    #{"ID" "OR"}
    #{"IL" "KY"}
    #{"IL" "MO"}
    #{"IL" "WI"}
    #{"IL" "IN"}
    #{"IL" "IA"}
    #{"IL" "MI"}
    #{"IN" "MI"}
    #{"IN" "OH"}
    #{"IN" "KY"}
    #{"IA" "NE"}
    #{"IA" "SD"}
    #{"IA" "WI"}
    #{"IA" "MN"}
    #{"IA" "MO"}
    #{"KS" "NE"}
    #{"KS" "OK"}
    #{"KS" "MO"}
    #{"KY" "TN"}
    #{"KY" "VA"}
    #{"KY" "WV"}
    #{"KY" "MO"}
    #{"KY" "OH"}
    #{"LA" "TX"}
    #{"LA" "MS"}
    #{"ME" "NH"}
    #{"MD" "VA"}
    #{"MD" "WV"}
    #{"MD" "PA"}
    #{"MA" "NY"}    
    #{"MA" "RI"}
    #{"MA" "VT"}
    #{"MA" "NH"}
    #{"MI" "OH"}
    #{"MI" "WI"}
    #{"MN" "ND"}
    #{"MN" "SD"}
    #{"MN" "WI"}
    #{"MS" "TN"}    
    #{"MO" "TN"}
    #{"MO" "NE"}
    #{"MO" "OK"}
    #{"MT" "SD"}
    #{"MT" "ND"}    
    #{"MT" "WY"}
    #{"NE" "SD"}
    #{"NE" "WY"}
    #{"NV" "OR"}
    #{"NV" "UT"}
    #{"NH" "VT"}    
    #{"NJ" "NY"}    
    #{"NJ" "PA"}
    #{"NM" "OK"}
    #{"NM" "TX"}    
    #{"NM" "UT"}
    #{"NY" "PA"}
    #{"NY" "VT"}
    #{"NC" "SC"}
    #{"NC" "TN"}
    #{"NC" "VA"}
    #{"ND" "SD"}
    #{"OH" "PA"}    
    #{"OH" "WV"}
    #{"OK" "TX"}
    #{"OR" "WA"}
    #{"PA" "WV"}
    #{"SD" "WY"}
    #{"TN" "VA"}
    #{"UT" "WY"}
    #{"WV" "VA"}
})

; (sort (distinct (flatten (map (partial into []) bordering-states))))
(def postal-abbreviations ["AL" "AR" "AZ" "CA" "CO" "CT" "DE" "FL" "GA" "IA" "ID" 
                           "IL" "IN" "KS" "KY" "LA" "MA" "MD" "ME" "MI" "MN" "MO" "MS" "MT" "NC" "ND" "NE" "NH" 
                           "NJ" "NM" "NV" "NY" "OH" "OK" "OR" "PA" "RI" "SC" "SD" "TN" "TX" "UT" "VA" "VT" "WA" 
                           "WI" "WV" "WY"])

(def all-pathways
  (combinations postal-abbreviations 4))

(defn connect-four? [pathway]
  (let [filtered-borders (->> 2
                             (combinations pathway)
                             (mapv set)
                             (filter bordering-states))]
    (and (= 3 (count filtered-borders))
         (= 4 (count (distinct (flatten (mapv (partial into []) filtered-borders))))))))

(def candidate-pathways
  (filter connect-four? all-pathways))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(defn clean-up-word [word]
  (clojure.string/replace word #"[^A-Z]" ""))

(def cmuwords
  (->> "resources/cmudict-0.7b.txt"
       slurp
       clojure.string/split-lines
       (map #(clojure.string/replace-first % #" " ""))
       (map #(clojure.string/split % #" " 2))
       (map (comp clean-up-word first))
       (into [])))

(def solution
  (for [p candidate-pathways
        :let [w (apply str p)
              filtered-words (filter (partial anagram? w) cmuwords)]
        :when (< 0 (count filtered-words))]
    [p filtered-words]))
