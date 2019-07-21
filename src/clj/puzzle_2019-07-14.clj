; https://www.npr.org/2019/07/14/741543390/sunday-puzzle-lucky-number-7

; Take an 11-letter word with two D's in it. If you drop both D's, you'll 
; get a world capital followed by a sign of the zodiac. What's the 11-letter word?

(defn has-two-ds? [word]
   (= 2 (get (frequencies word) \d)))

(defn remove-ds [word]
  (clojure.string/replace word #"d" ""))

(def zodiac-signs
  #{"AQUARIUS" "PISCES" "ARIES" "TAURUS" "GEMINI" "CANCER" 
    "LEO" "VIRGO" "LIBRA" "SCORPIO" "SAGITTARIUS" "CAPRICORN"})

(defn check-for-signs [word]
  (->> zodiac-signs
       (map clojure.string/lower-case)
       (map #(re-find (re-pattern %) word))
       (remove nil?)
       empty?
       not))

(def fodder
   (->> "resources/twl06.txt"
        slurp
        clojure.string/split-lines
        (map #(-> % (clojure.string/split #" ") first))
        (map #(clojure.string/replace % #"[^a-z]" ""))
        (filter (comp (partial = 11) count))
        (filter has-two-ds?)
        (filter check-for-signs)
        distinct))

(def solution
  (map (juxt identity remove-ds) fodder))

