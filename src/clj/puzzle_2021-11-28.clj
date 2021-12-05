; https://www.npr.org/2021/11/28/1059517658/sunday-puzzle-br-ing-it-on

; Take the 9 letters of EARTH SIGN. Repeating them as often as 
; necessary, you can spell the four-word title of a classic movie 
; in 15 letters. You can also use them to spell the four-word title 
; of a classic song in 19 letters. What two titles are these?

(def movies (->> "resources/movies3.txt"
                 slurp
                 clojure.string/split-lines
                 (map clojure.string/lower-case)
                 (map #(clojure.string/replace % #" " ""))))

(def mtsf (->> movies
               (filter (partial re-find #"^[earthsign]+$"))
               (filter (comp (partial = 15) count))))

(def songs (->> "resources/classic_rock_song_titles.txt"
                slurp
                clojure.string/split-lines
                (map clojure.string/lower-case)
                (mapv #(clojure.string/replace % #" " ""))
                ))

(def stf (->> songs
              (filter (partial re-find #"^[earthsign]+$"))
              (filter (comp (partial = 19) count))))

; egrep '^[earthsignEARTHSIGN_]{22}$' enwiki-20200820-all-titles-clean.txt
