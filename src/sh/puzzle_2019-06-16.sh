# https://www.npr.org/2019/06/16/733093434/sunday-puzzle-doh

# Name a major U.S. city with a population of more than 100,000. 
# It has a two-word name. The two words rhyme, respectively, with 
# the first and last names of a famous singer. What city is it, 
# and who's the singer?

more resources/usa_cities.txt | awk 'BEGIN {FS="\t"} {print $2}' | grep ' '
