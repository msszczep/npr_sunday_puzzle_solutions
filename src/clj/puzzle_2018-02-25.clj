; https://www.npr.org/2018/02/25/588417055/sunday-puzzle-reverse-to-get-ahead

; Name a place in the United States that contains a W. 
; Drop the W, and you can rearrange the remaining letters 
; to name two types of mammals, each in the plural form. 
; What place is it, and what are the mammals?

(def answer
  (let [w-places 
          (->> "/home/mitchells/Desktop/npr_sunday_puzzle_solutions/resources/US.txt"
               slurp
               clojure.string/split-lines
               (map #(-> %
                         clojure.string/lower-case 
                         (clojure.string/split #"\t")
                         (nth 2)
                         (clojure.string/replace #" " "")))
               (filter (partial re-find #"w"))
               ; (filter (partial re-find #"s.*s"))               
               set)
        w-states #{"delaware" "hawaii" "iowa" "newhampshire" "newjersey"
                   "newmexico" "newyork" "washington" "westvirginia"
                   "wisconsin" "wyoming"}
        mammals 
          #{"platypuses" "echidnas" "anteaters" "bandicoots" "bettongs" 
            "gliders" "koalas" "wombats" "moles" "armadillos"
            "shrews" "tenrecs" "sloths" "bats" "baboons" "apes" 
            "lemurs" "gibbons" "monkeys" "gorillas" "indris" "tarsiers"
            "vervets" "aardwolfs" "badgers" "wolves" "bears" "bobcats"
            "dogs" "dingos" "dholes" "foxes" "cats" "coyotes" "cougars"
            "cheetahs" "leopards" "hyenas" "ermines" "jackals" "jaguars"
            "lions" "lynxes" "meerkats" "minks" "pumas" "tigers" "weasels"
            "wildcats" "seals" "walruses" "whales" "dolphins" "narwhals"
            "orcas" "manatees" "elephants" "asses" "burros" "donkeys"
            "horses" "mules" "mustangs" "zebras" "ponies" "rhinos" "tapirs"
            "hyraxes" "aardvarks" "alpacas" "camels" "goats" "buffalos"
            "buffalo" "sheep" "deer" "elks" "elk" "llamas" "llama"
            "moose" "ibexes" "okapi" "oxen" "oxes" "stags" "pigs" "yaks"
            "zebus" "mice" "lemmings" "rats" "chipmunks"
            "squirrels" "hamsters" "voles" "rabbits" "hares"
            "okapis" "deers" "kangaroos" "belugas"}]
    (for [w w-states
          m1 mammals
          m2 mammals
          :when (and (not= m1 m2)
                     (= (frequencies w) 
                        (frequencies (str m1 m2 \w))))]
      [w m1 m2]))) 

