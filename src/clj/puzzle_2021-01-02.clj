; https://www.npr.org/2022/01/02/1069562198/sunday-puzzle-happy-new-ear

; Take the name of a certain vegetable. Move the 7th, 5th, and 6th 
; letters — in that order — to the front of the word. Phonetically 
; you'll name another vegetable. What vegetables are these?

(def vegetables
     ["alfalfa" "artichoke" "arugula" "asparagus" "aubergine" "bamboo shoot" "bean" "bean sprout" "bok choy" "Bermuda onion" "black-eyed pea" "broccoli" "cabbage" "Brussels sprout" "cabbage" "cactus" "cardoon" "carrot" "cassava" "cauliflower" "celeriac" "celery" "chayote" "chicory" "Chinese cabbage" "chive" "collard greens" "corn" "courgette" "cowpea" "cress" "cucumber" "daikon" "dandelion greens" "eggplant" "endive" "escarole" "finocchio" "green bean" "green onion" "Japanese eggplant" "Jerusalem artichoke" "kale" "kohlrabi" "leek" "lettuce" "lovage" "maize" "manioc" "mung sprout" "mushroom" "mustard greens" "nopal" "nori" "okra" "onion" "oyster plant" "pumpkin" "potato" "pearl onion" "pepper" "pea" "parsnip" "radiccio" "radish" "rampion" "ramson" "red cabbage" "red onion" "rhubarb" "rutabaga" "salsify" "scallion" "seaweed" "shallot" "snap bean" "sorrel" "soybean" "spinach" "sprout" "squash" "string bean" "succory" "sugar pea" "sweet potato" "Swiss chard" "taro root" "tomatillo" "tomato" "truffle" "turnip" "water chestnut" "watercress" "wax bean" "yam" "zucchini"])

(defn clean-up-word [word]
  (clojure.string/replace (clojure.string/lower-case word) #"[^a-z]" ""))

(defn move-letters [word]
  (let [[a b c d e f g & h] (map char (clean-up-word word))]
    (apply str g e f a b c d h)))
 
(def solution
  (->> vegetables
       sort
       (map (juxt identity move-letters))
       ))
