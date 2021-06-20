# https://www.npr.org/2021/06/20/1008352915/sunday-puzzle-hidden-cities

# Name a make of car. Write it in all capital letters. Rotate one of the 
# letters 90 degrees and another letter 180 degrees to make a woman's 
# name. What is it?

more cars_uniq_sorted.csv | awk 'BEGIN {FS=","} {print toupper($1)}' | sort | uniq | grep 'Z'
