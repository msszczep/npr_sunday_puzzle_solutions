#!/usr/bin/python
# -*- coding: utf-8 -*-

#http://www.npr.org/2013/06/09/189929980/follow-homer-to-find-your-way

# Name a movie in two words — five letters in each word. Both words start with vowels. 
# Take one letter in the first word, move it two spaces later in the alphabet, and rearrange the result. 
# You'll get the second word in the movie's title. What movie is it?

def filter_words(words):
	vowels = set(['A', 'E', 'I', 'O', 'U'])
	if len(words) != 2:
		return False
	if len(words[0]) == 5 and len(words[1]) == 5 and words[0][0].upper() in vowels and words[1][0].upper() in vowels:
		return True
	return False 

def main():
	import re
	results = set()
	p = re.compile('^(.*) \(')
	for movie in open('movies.list'):
		m = re.findall(p, movie[:-1])
		try:
			words = m[0].split(' ')
		except:
			words = []
		if filter_words(words) == True:
			results.add(m[0])
	return results

if __name__ == "__main__":
	results = main()
	for r in results:
		print r
