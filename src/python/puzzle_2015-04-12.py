#!/usr/bin/python

"""
http://www.npr.org/2015/04/12/399070578/be-attentive-and-youll-find-tv-in-these-words

Think of a job, in 8 letters, that names someone who might work with actors. Change 
one letter in this to the following letter of the alphabet to name another person who 
works with actors. What jobs are these?
"""

words = map(lambda x: x[:-1], open('ospd3.txt').readlines())
print words[0:12]
#    w = row[:-1]
#    if len(w) == 4 and w[0].isupper() and w[1:].islower():
#        words.add(w)
#print len(words)
#j = 0 
#for word in words:
#    temp_word = word[0] + word[2] + word[1] + word[3]
#    if temp_word in words and temp_word != word and word[0] in vowels and word[3] in vowels:
#        final_answer.append([word, temp_word])
#    j = j + 1 
#print len(final_answer)
#for f in sorted(final_answer):
#    print f 
