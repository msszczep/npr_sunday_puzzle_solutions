#!/usr/bin/python

"""
http://www.npr.org/2015/02/01/382889760/the-ol-puzzle-switcheroo

Think of a well-known place name in the U.S. that's four letters long. 
Switch the second and third letters to get a well-known place name in Europe. What is it?
"""

vowels = set(['a', 'e', 'i', 'o', 'u'])
words = set() 
final_answer = []
for row in open('/Users/msszczep1/Desktop/nyc_stuff/enwiki-20131001-all-titles').readlines():
    w = row[:-1]
    if len(w) == 4 and w[0].isupper() and w[1:].islower():
        words.add(w)
#print len(words)
j = 0 
for word in words:
    temp_word = word[0] + word[2] + word[1] + word[3]
    if temp_word in words and temp_word != word and word[0] in vowels and word[3] in vowels:
        final_answer.append([word, temp_word])
    j = j + 1 
#print len(final_answer)
for f in sorted(final_answer):
    print f 
