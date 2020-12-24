# https://www.npr.org/2020/12/13/945857578/sunday-puzzle-word-sandwiches

# Using only the letters in the phrase RIDE ON — repeating them as often 
# as necessary — you can spell 1) the one-word proper name of a famous 
# fictional animal, and 2) a word for what kind of animal it is. What's 
# the name of the animal, and what's the word?

more resources/cmudict-0.7b.txt | awk '{print $1}' | egrep '^[RIDEON]+$'
