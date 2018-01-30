#!/usr/bin/python

"""
http://www.npr.org/2013/06/02/187897600/keep-your-i-on-the-prize

Can you name three common three-letter words that are all synonyms and which together consist of nine different letters of the alphabet? 
Here's a hint: The letters A and O are not used.
"""

import urllib2
import json

def get_synonyms(word):
	to_return = []
	url = 'http://words.bighugelabs.com/api/2/a7e4f417cb56417caf5f1fab8afa215c/%s/json' % word
	r = urllib2.urlopen(url)
	l = r.readlines()
	t = json.loads(l[0])
	for k, v in t.iteritems():
		for k2, v2 in v.iteritems():
			if k2 == 'syn':
				for w in v2:
					if len(w) == 3:
						to_return.append(w)	
	return to_return

def main():
	to_return = []
        words = open('three_synonyms.txt').readlines()
        for word in words:
		s = set()
		for let in word[:-1]:
			s.add(let)
		if len(s) == 3:
			to_return.append(word[:-1])
	return to_return	

if __name__ == "__main__":
	words = main()
	for word in words:
		try:
			r = get_synonyms(word)
			print word, r
		except:
			pass
