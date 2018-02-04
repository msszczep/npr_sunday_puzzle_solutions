#!/usr/bin/python

"""
http://www.npr.org/2015/05/10/405187702/for-this-puzzle-j-st-ign-r-th-v-w-ls

This challenge comes from listener Rudy Simons of Southfield, Mich. The letters 
of the one-syllable word "groan" can be rearranged to spell "organ," which has 
two syllables. Here's the challenge: Think of a common one-syllable, five-letter 
word whose letters can be rearranged to spell a common two-syllable word -- and 
then rearranged again to spell a common three syllable word. I have two different 
answers in mind, and it's possible there are others, but you only have to think of 
one.
"""

cmudict = {} 
for c in open('cmudict').readlines():
    c_split = c.split()
    cmudict[c_split[0].lower()] = len(filter(lambda x: x[0] in ['A', 'E', 'I', 'O', 'U'], c_split[1:])) 
words = set(map(lambda x: x[:-1], open('ospd3.txt').readlines())) 
fives = filter(lambda x: len(x) == 5, words)
fives_dict = {} 
for f in fives:
    alphagram = ''.join(sorted(f)) 
    try:
        fives_dict[alphagram].append(f)
    except:
        fives_dict[alphagram] = [f] 
for k, v in fives_dict.iteritems():
    try:
        ns = set(map(lambda x: cmudict[x], v))
    except:
        ns = set() 
    if ns == set([1, 2, 3]): 
        print k, v, ns 
