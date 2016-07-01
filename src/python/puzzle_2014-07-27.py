#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2014/07/27/335590211/a-flowery-puzzle-for-budding-quizmasters

There are three popular men's names, each six letters long, that differ by only their first letters. 
In other words, the last five letters of the names are all the same, in the same order. 
Of the three different first letters, two are consonants and one is a vowel. What names are these?

"""

def main():
    sixes = {}
    words = open('male_first_names_lower.txt').readlines()
    for word in words:
        word = word[:-1]
        if len(word) == 6:
            try:
                sixes[word[1:]].append(word)
            except:
                sixes[word[1:]] = [word]
    i = 1
    for k, v in sixes.iteritems():
        if len(v) == 3:
            print i, k, v
            i = i + 1

if __name__ == "__main__":
    main()
