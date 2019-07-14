

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

