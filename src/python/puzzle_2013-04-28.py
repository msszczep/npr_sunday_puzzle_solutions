#!/usr/bin/python

"""
http://www.npr.org/2013/04/28/179515080/as-you-know-puzzles-are-a-pastime

The first 12 letters of the alphabet are A to L. Think of a familiar, six-word proverb that contains 11 of these 12 letters. The letters may be used more than once, and you may use additional letters from the second half of the alphabet. What proverb is this?
"""
def is_ok(proverb):
	u = set(proverb[:-1].lower().replace(' ', '').replace(',', ''))
	score = 0
	for l in u:
		if l in ['a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l']:
			score = score + 1
	if score == 11:
		print u
		return True 
	return False 

def main():
	proverbs = open('proverbs2.txt').readlines()
	i = 0
	for proverb in proverbs:
		if i > 10000:
			continue
		words = proverb[:-1].split(' ')
		if len(words) == 6:
			if is_ok(proverb):		
				print words 
		i = i + 1

if __name__ == "__main__":
	main()
