#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2014/01/05/259721680/two-times-harder

Name something in five letters that's generally pleasant, it's a nice thing to have. Add the letters A and Y, 
and rearrange the result, keeping the A and Y together as a pair. You'll get the seven-letter word that names 
an unpleasant version of the five-letter thing. What is it?

"""

def check_anagrams(fiver, sevener):
	if ''.join(sorted(fiver)) == ''.join(sorted(sevener)):	
		return True
	return False

def main():
	sevens = set() 
	fives = set()
        words = open('ospd3.txt').readlines()
        for word in words:
		word = word[:-1]
		if len(word) == 7:
			if word.find('ay') > -1:
				sevens.add(word.lower())
		if len(word) == 5:
			fives.add(word.lower())
	i = 0
	for seven in sevens:
		if i % 50 == 0:
			print i
		i = i + 1
		for five in fives:
			if check_anagrams(five, seven.replace('ay', '')):
				print five, seven 

if __name__ == "__main__":
        main()
