#!/usr/bin/python3

"""
https://www.npr.org/2021/04/11/986093540/sunday-puzzle-whats-the-show

Think of part of the body in seven letters. Add an "N" and rearrange all the letters 
to name two more parts of the body (none related to the original word). What body 
parts are these?
"""

def issubanagram(subset, mainset):
    for item in [c in mainset for c in subset]:
        if not item:
            return False
    for item in [False for difference in [mainset.count(l) - subset.count(l) for l in subset] if difference < 0]:
        if not item:
            return False
    return True

bp = map(lambda x: x[:-1], open('body_parts.txt').readlines())
allwords = []
for i in bp:
    allwords.append(i)

s = filter(lambda x: len(x.replace(' ', '')) == 7, allwords)
sevens = []
for i in s:
    sevens.append(i)

for v in sevens:
    vresult = []
    subanagrams = filter(lambda x: issubanagram(x.lower(), v.lower() + 'n'), allwords)
    for i in subanagrams:
        vresult.append(i)
    print(v, vresult)

