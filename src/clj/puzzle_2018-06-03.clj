; https://www.npr.org/2018/06/03/616080244/sunday-puzzle-its-up-to-you

; The object is to pitch an idea to one of the networks, either 
; broadcast or cable, in which your show's title is just one letter 
; different from an existing show's title, past or present. Name your 
; TV show and summarize it in 15 words or less. Entries will be judged 
; on their sense, naturalness of wording, humor and overall effect. 

; Buffy the Vampire Player: Buffy dates vampires Angel and Spike at the same time.
; Fad Men
; Tattlestar Galactica: a starship flees from the Cylons and gossips on everyone in the galaxy.
; The West King
; The Handmaid's Sale
; Saturday Fight Live
; Saturday Night Love
; Drugnet
; Moonfighting
; My So-Called Wife
; The Old Couple
; Yeastmaster: Dar learns to bake bread to give to his beloved, Kyra.
; Encourage: Vince says helpful words to his friends trying to make it in Hollywood
; Inspector Gidget: Larue helps her friend become a cyborg to defeat Dr. Claw

(def tv-shows
   (->> "resources/tv_series.txt"
        slurp
        clojure.string/split-lines
        set))


(defn edit-distance-one? [w1 w2]
  (->> (interleave w1 w2)
       (partition 2)
       (remove #(= (first %) (last %)))
       count
       (= 1)))


(def dict-words 
  (->> "resources/ni2.txt"
       slurp
       clojure.string/split-lines
       (map clojure.string/lower-case)
       set))


(defn get-words-edit-distance-one [word]
  (->> dict-words
       (filter (comp (partial = (count word)) count))
       (filter (partial edit-distance-one? word))
       sort))
