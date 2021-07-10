# https://www.npr.org/2021/06/27/1010586683/sunday-puzzle-words-within-words

# Take the name of a major American city. Hidden inside it in 
# consecutive letters is the name of a Japanese food. Remove 
# that. The remaining letters can be rearranged to to spell 
# some Mexican foods. Name the city and the foods.

# https://www.hotels.com/go/japan/best-japanese-foods

more resources/usa_cities.txt | awk 'BEGIN {FS="\t"} {print $2}' | tr '[:upper:]' '[:lower:]' | sed 's/ //g' | grep ramen
