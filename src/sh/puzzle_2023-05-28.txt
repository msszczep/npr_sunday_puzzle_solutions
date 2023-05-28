# https://www.npr.org/2023/05/28/1178597483/sunday-puzzle-bridge-those-words

# Think of a well-known author whose first name is nine letters long, 
# and last name six letters. Change the first letter of the last name 
# and anagram those six letters to spell a word. Now read everything 
# together — the author's first name plus the anagram with a letter 
# changed of the last name — and you'll get a certain professional athlete. 
# Who is it?

time more enwiki-20200820-all-titles-clean.txt | egrep '^[A-Za-z]{9}_[A-Za-z]{6}$' > 96-2.txt
