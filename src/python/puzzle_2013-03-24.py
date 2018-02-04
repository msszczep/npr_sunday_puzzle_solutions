#!/usr/bin/python

"""
http://www.npr.org/2013/03/24/175144673/finding-the-answers-within

Take the four words "salt," "afar," "lava" and "trap." Write them one under the other, and the words will read the same vertically as horizontally. This is a word square of four-letter words. Note that the only vowel in this example square is an A. The object of the challenge is to create a five-letter word square using only common, uncapitalized English words, in which the only vowel in the entire square is A. The word in the center row, and column, is NASAL.
"""

import re

square = [['?', 'a', 'n', '?', '?'],
	  ['a', '?', 'a', '?', '?'], 
	  ['n', 'a', 's', 'a', 'l'], 
	  ['?', '?', 'a', '?', '?'], 
	  ['ahslt', 'aptn', 'l', '?', 'adlstr']]

s2 = ['[abcdfghjklmnpqrstvwxz]an[abcdfghjklmnpqrstvwxz]{2}', 'a[abcdfghjklmnpqrstvwxz]a[abcdfghjklmnpqrstvwxz]{2}', 'nasal', '??a??', '??l??']

# http://www.gtoal.com/scrabble/kragen/msg00013.html
# http://programmingpraxis.com/2011/05/03/squaring-the-bishop/#comment-2982

def get_vertical_words(words):
	to_return = []
	for i in range(len(words)):
		word = words[0][i] + words[1][i] + words[2][i] + words[3][i] + words[4][i]
		to_return.append(word)
	return to_return

def get_candidates(cws1, cws2, cws3, cws4, sset):
	import sys
	for cw1 in cws1:
		for cw2 in cws2:
			for cw3 in cws3:
				for cw4 in cws4:
					vws = get_vertical_words([cw1, cw2, 'nasal', cw3, cw4])
					if vws[0] == cw1 and vws[1] == cw2 and vws[2] == 'nasal' and vws[3] == cw3 and vws[4] == cw4: 
						print vws 

def get_start_set():
	rows = open('ospd3.txt').readlines()
	to_return = []
	for row in rows:
		row = row[:-1]
		if len(row) == 5 and re.search('^[abcdfghjklmnpqrstvwxyz]{5}$', row):
			to_return.append(row)
	return to_return	

def filter_set(rows, f):
	to_return = []
	for row in rows:
		if re.search(f, row):
			to_return.append(row)
	return to_return 

a = get_start_set()
#r = get_candidates(a, s2)
#print r 
b = filter_set(a, '^[abcdfghjklmnpqrstvwxyz]{2}n[abcdfghjklmnpqrstvwxyz]{2}$')
#print b
#print ""
#print second_letter
c = filter_set(a, '^[abcdfghjklmnpqrstvwxyz]{2}a[abcdfghjklmnpqrstvwxyz]{2}$')
#print c
d = filter_set(a, '^[abcdfghjklmnpqrstvwxyz]{2}l[abcdfghjklmnpqrstvwxyz]{2}$')
#e = filter_set(a, '^[abcdfghjklmnpqrstvwxz]{1}an[abcdfghjklmnpqrstvwxz]{2}$')
#f = filter_set(a, '^[ahslt][apnt]l[abcdfghjklmnpqrstvwxz]{2}$')
#g = filter_set(a, '^[abcdfghjklmnpqrstvwxz]an[abcdfghjklmnpqrstvwxz][ahslt]$')
#print b
#print c
#print d
r = get_candidates(b, c, c, d, a)
#print g
#print len(g)
#second_letter = set()
#for word in g:
#	second_letter.add(word[4])
#print second_letter 
