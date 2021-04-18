#https://www.npr.org/2021/04/18/988385478/sunday-puzzle-vowel-replacement

#Name a famous actor — 4 letters in the first name, 7 letters in the last. 
#You can change the first letter of the actor's first name to name a bird. 
#And you can change the first letter of the actor's last name to name a 
#mammal. Who's the actor?

egrep '^[A-Za-z]{7}\, [A-Za-z]{4}$' who2.txt
