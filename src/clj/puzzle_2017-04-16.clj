;;

;; The name of what famous entertainer -- first and last name
;; -- has a two-word spoonerism meaning "A runny variety of cheese"?

(->> (slurp (clojure.java.io/resource "cmudict-0.7b.txt"))
     clojure.string/split-lines               ;; split lines
     (map #(clojure.string/split % #" " 4))   ;; split line by space
     (map #(filter (partial not= "") %))      ;; remove extra space
     (filter (comp (partial re-find #"^[A-Z]+$") first))
                                              ;; remove punctuation
     (map (partial zipmap [:word :onset :remainder]))
                                              ;; set as map with keywords
     (group-by :remainder)                    ;; group by remainder
     (filter (comp (partial < 1) count val))  ;; remove singletons
     (remove (comp nil? key))                 ;; remove remainder-less entries
     vals                                     ;; keep data
     (group-by #(->> % (map :onset) set))     ;; group by sets of onsets
     (filter (comp (partial < 1) count val))  ;; remove singletons
     vals                                     ;; keep data
     (map (comp (partial map :word) flatten)) ;; just show words
     )

; carrots and peas
; parrots and keys

