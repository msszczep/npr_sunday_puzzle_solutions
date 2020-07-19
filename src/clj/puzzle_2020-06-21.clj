; https://www.npr.org/2020/06/21/881226297/sunday-puzzle-new-start

; Think of a famous person whose name consists of three names. The first 
; and last letters of the first name plus the first and last letters of the 
; second name plus the first and last letters of the third name, in order, 
; name a city and lake in Europe. Who is it?

(def three-word-names
  ["Evan Rachel Wood"
   "Alexander Graham Bell"
    "Andrew Lloyd Webber"
    "Anna Nicole Smith"
    "Arthur Conan Doyle"
    "Billy Bob Thornton"
    "Billy Ray Cyrus"
    "Catherine Zeta Jones"
    "David Lee Roth"
    "Edgar Allan Poe"
    "Edgar Lee Masters"
    "Edgar Rice Burroughs"
    "Elizabeth Barrett Browning"
    "Emmy Lou Harris"
    "Erle Stanley Gardner"
    "Francis Scott Key"
    "Frank Lloyd Wright"
    "George Bernard Shaw"
    "George Washington Carver"
    "Haley Joel Osment"
    "Hans Christian Andersen"
    "Harriet Beecher Stowe"
    "Henry David Thoreau"
    "Henry Wadsworth Longfellow"
    "James Earl Jones"
    "James Fenimore Cooper"
    "Jamie Lee Curtis"
    "Jennifer Love Hewitt"
    "Jerry Lee Lewis"
    "Jo Dee Messina"
    "Johann Sebastian Bach"
    "John Greenleaf Whittier"
    "John Jacob Astor"
    "John Paul Jones"
    "John Quincy Adams"
    "Joyce Carol Oates"
    "Lee Ann Womack"
    "Louisa May Alcott"
    "Martin Luther King"
    "Wolfgang Amadeus Mozart"
    "Mary Lou Retton"
    "Neil Patrick Harris"
    "Oliver Wendell Holmes"
    "Ralph Waldo Emerson"
    "Robert Louis Stevenson"
    "Robert Penn Warren"
    "Sarah Jessica Parker"
    "Sarah Michelle Gellar"
    "Jeffrey Dean Morgan"
    "Stevie Ray Vaughn"
    "Tommy Lee Jones"
    "William Carlos Williams"
    "William Henry Harrison"
    "William Howard Taft"
    "Paul Thomas Anderson"
    "Philip Seymour Hoffman"
    "Chad Michael Murray"
    "Daniel Dae Kim"
    "Alexandra Maria Lara"
    "Melissa Joan Hart"
    "Rae Dawn Chong"
    "Lisa Marie Presley"
    "Jonathan Taylor Thomas"
    "Malcolm Jamal Warner"
    "Melissa Joan hart"
    "Joseph Gordon Levitt"
    "Charles Nelson Reilly"
    "Edward Everett Horton"
    "Sally Anne Howes"
    "Mary Elizabeth Mastrantonio"
    "Pamela Sue Martin"
    "Edward James Olmos"
    "Ruth Bader Ginsburg"
    "Joe Don Baker"
    "Lou Diamond Phillips"
    "John Wilkes Booth"
    "Henry Ross Perot"
    "Maria Conchita Alonso"
    "Rae Dawn Chong"
    "Tammy Faye Bakker"
    "Melissa Sue Anderson"
    "Mary Tyler Moore"])

(defn take-first-and-last-letter [s]
  (let [u (clojure.string/lower-case s)] 
    (vector (first u) (last u))))

(defn get-six-letter-word [n]
  (->> #" "
       (clojure.string/split n)
       (mapv take-first-and-last-letter)
       flatten
       (apply str)))

(clojure.pprint/pprint (mapv (juxt identity get-six-letter-word) 
                             (sort (distinct three-word-names))))

