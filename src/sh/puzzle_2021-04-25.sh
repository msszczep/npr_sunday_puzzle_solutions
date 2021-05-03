# https://www.npr.org/2021/04/25/990473656/sunday-puzzle-2-part-puzzles

# Think of a person in the news (5,4). The first name and last name each 
# have at least two consonants and two vowels. All the consonants in each 
# name come at the start, and all the vowels come at the end. The letter 
# "y" is not used. Who is this famous person?

more all_en_wikipedia_titles_20210420.txt | egrep '^[BCDFGHIJKLMNPQRSTVWXZbcdfghijklmnpqrstvwxz]{2}[a-z][aeiou]{2}_[BCDFGHIJKLMNPQRSTVWXZbcdfghijklmnpqrstvwxz]{2}[aeiou]{2}$'
