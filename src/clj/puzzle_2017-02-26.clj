(->> (partition 5 1 "abcdefghijklmnopqrstuvwxyz")
     (map (partial apply str)))

"jack lemmon"
