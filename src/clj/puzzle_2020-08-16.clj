; https://www.npr.org/2020/08/16/902845585/sunday-puzzle-city-shuffle

; Think of a major city in France whose name is an anagram of a major 
; city in Italy. Each city has more than 100,000 people.

; https://www.wolframalpha.com/input/?i=cities+in+italy+with+population+at+least+100%2C000

; https://www.wolframalpha.com/input/?i=cities+in+france+with+population+at+least+100%2C000

(def italy "Rome | Milan | Naples | Turin | Palermo | Genoa | Bologna | Florence | Bari | Catania | Venice | Verona | Messina | Padova | Trieste | Taranto | Brescia | Parma | Prato | Modena | Reggio di Calabria | Reggio nell'Emilia | Perugia | Ravenna | Leghorn | Cagliari | Foggia | Rimini | Salerno | Ferrara | Sassari | Latina | Monza | Syracuse | Bergamo | Pescara | Forlì | Trento | Vicenza | Terni | Bolzano | Giugliano in Campania | Novara | Piacenza | Ancona | Andria")

(def france "Paris | Marseille | Lyon | Toulouse | Nice | Nantes | Montpellier | Strasbourg | Bordeaux | Lille | Rennes | Reims | Saint-Etienne | Le Havre | Toulon | Grenoble | Dijon | Angers | Nîmes | Villeurbanne | Le Mans | Brest | Aix-en-Provence | Clermont-Ferrand | Tours | Limoges | Amiens | Perpignan | Boulogne-Billancourt | Besancon | Metz | Orleans | Saint-Denis | Mulhouse | Rouen | Argenteuil | Caen | Nancy | Montreuil")

(defn normalize-term [word]
  (->> ""
       (clojure.string/replace word #"[^a-zA-Z]")
       clojure.string/lower-case))

(defn normalize [cities]
  (->> #"\|"
       (clojure.string/split cities)
       (mapv normalize-term)))

(defn anagram? [w1 w2]
  (= (frequencies w1) (frequencies w2)))

(def final-answer
  (for [i (normalize italy)
        f (normalize france)
        :when (anagram? f i)]
       [f i]))
