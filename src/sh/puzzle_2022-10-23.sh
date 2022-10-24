# [I]t's unusual for a multi-word movie title to consist entirely of words starting with vowels, none of which are the article "a." or pronoun "I." Can you name a popular movie with a five-word title — with word lengths 10, 10, 3, 2, 4 — all of which start with vowels?

more enwiki-20200820-all-titles-clean.txt | egrep '^[AEIOUaeiou][A-Za-z]{9}_[AEIOUaeiou][A-Za-z]{9}_[AEIOUaeiou][A-Za-z]{2}_[AEIOUaeiou][A-Za-z]{1}_[AEIOUaeiou][A-Za-z]{3}$'
