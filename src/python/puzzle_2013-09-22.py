#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2013/09/22/224547625/play-the-blame-game

The name of what character, familiar to everyone, contains each of the five vowels (A, E, I, O and U) exactly once? 
The answer consists of two words — eight letters in the first word, four letters in the second.
"""

def check_vowels(d):
	to_return = 0
	vs = ['a', 'e', 'i', 'o', 'u']
	for v in vs:
		if d.has_key(v):
			to_return = to_return + 1	
			if d[v] == 1:
				to_return = to_return + 1
	if to_return == 10:
		return True
	return False 

def check_words(w1, w2):
	lets = {} 
	for w in [w1, w2]:
		for l in w:
			try:
				lets[l] = lets[l] + 1
			except:
				lets[l] = 1
	return check_vowels(lets)

def main():
	eights = set() 
	fours = set() 
        words = open('ospd3.txt').readlines()
        for word in words:
		word = word[:-1]
		if len(word) == 8:
			eights.add(word.lower())
		if len(word) == 4:
			fours.add(word.lower())
	for four in fours:
		for eight in eights:
			if check_words(eight, four):
				print eight, four

if __name__ == "__main__":
	#print check_words('veronica', 'murs')
        main()
