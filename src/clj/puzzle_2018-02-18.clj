; https://www.npr.org/2018/02/18/585772621/sunday-puzzle-end-rhymes

; Take the start of the name of a country and the end of that country's
; capital.  Put the parts together, one after the other, and you'll get
; the last name of a character in a very popular movie.  It's a character
; everyone knows.  Who is it?

(def answer
  (let [c-and-c 
         (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/world-capitals.txt"
              slurp
              clojure.string/split-lines
              (map #(-> %
                        clojure.string/lower-case 
                        (clojure.string/split #","))))
        surnames
          (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/cmudict-0.7b.txt"
              slurp
              clojure.string/split-lines
              (map #(-> %
                        (clojure.string/split #"  ")
                        first
                        clojure.string/lower-case))
              set)]
    (letfn [(get-candidates [first-candidates second-candidates]
              (for [x first-candidates
                    y second-candidates]
                (str x y)))]
      (for [[country capital] c-and-c]
         (let [country-substrings (map #(subs country 0 %) (range 2 (count country)))
               capital-substrings (map #(subs capital % (count capital))
                                       (range 1 (dec (count capital))))
               candidates (get-candidates country-substrings capital-substrings)
               filtered-candidates (filter surnames candidates)]
           (when (pos? (count filtered-candidates))
               [country capital filtered-candidates]))))))

