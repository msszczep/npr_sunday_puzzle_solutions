#!/usr/bin/python

"""
http://www.npr.org/2013/05/26/186664592/investigating-the-crime-scene

Think of a word starting with G. Change the G to a T, and rearrange the letters after the T. The result will be a new word with the same 
meaning as the original word. What words are these?
"""

def get_word_dict(w):
        t = {}
        for c in w:
             try:
                   t[c] = t[c] + 1
             except:
                   t[c] = 1
        return t

def compare_word_dicts(d1, d2):
        for k in d1.iterkeys():
             try:
                   if d1[k] != d2[k]:
                        return False
             except:
                   return False
        for k in d2.iterkeys():
             try:
                   if d1[k] != d2[k]:
                        return False
             except:
                   return False
        return True

def compare_words(w1, w2):
        if w1[1:] == w2[1:]:
             return False
        r1 = get_word_dict(w1[1:])
        r2 = get_word_dict(w2[1:])
        return compare_word_dicts(r1, r2)

def main():
        g = set()
        t = set()
        words = open('ospd3.txt').readlines()
        for word in words:
                if word[0] == 'g':
                     g.add(word[:-1])
                if word[0] == 't':
                     t.add(word[:-1])
        for gword in g:
              for tword in t:
                   if compare_words(gword, tword) == True:
                        print gword, tword

if __name__ == "__main__":
        main()
