#!/usr/bin/python

# http://www.npr.org/2013/06/23/194666735/keep-calm-as-a-clam

#Write down these five words: "aide," "heart," "tough," "gelatin" and "emanate." 
#There is something very unusual they have in common. What is it? And what's another word with this property?
# Answer: aide -> idea, heart -> earth, etc.

rows = open('ospd3.txt').readlines()
words = set()
for row in rows:
	words.add(row[:-1])
for w in words:
	w2 = w[1:] + w[0]
	if w2 in words:
		print w, w2
