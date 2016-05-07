"""
Think of a three-syllable word in four letters, add two letters and rearrange everything to become a two-syllable word in six 
letters. Then add two more letters and scramble them to get a one syllable word in eight letters.

http://www.npr.org/2013/07/21/203994762/the-price-of-fame-a-scrambled-name
"""

vowels = ['AA', 'AH', 'EH', 'IH', 'OW', 'UH', 'AE', 'AO', 'AY', 'IY', 'ER', 'EY', 'AW', 'OY', 'UW']

def count_syllables(phonemes):
	result = 0 
	for phoneme in phonemes:
		phoneme = phoneme.replace('0', '').replace('1', '').replace('2', '')
		if phoneme in vowels:
			result = result + 1
	return result

def main(len_word, num_syllables):
	to_return = []
	lines = open('cmudict.txt', 'r')
	for line in lines:
		a = line.split(' ', 1)
		word = a[0]
		phonemes = a[1][:-1].split(' ')
		if len(word) == len_word and count_syllables(phonemes) == num_syllables:
			to_return.append(word)
	return to_return	

def make_dict(s):
	d = {}
	for c in s:
		try:
			d[c] = d[c] + 1
		except:
			d[c] = 1
	return d	

def compare_sets(s1, s2):
	to_return = True
	d1 = make_dict(s1)
	d2 = make_dict(s2)
	for key in d1.iterkeys():
		try:
			if d1[key] > d2[key]:
				to_return = False
		except:
			to_return = False
	return to_return

def get_words():
	set1 = main(4, 3)
	set2 = main(6, 2)
	set3 = main(8, 1)
	for w3 in set3:
		for w2 in set2:
			if compare_sets(w2, w3):
				for w1 in set1:
					if compare_sets(w1, w2): 
						print w1, w2, w3

if __name__ == "__main__":
	get_words()	
