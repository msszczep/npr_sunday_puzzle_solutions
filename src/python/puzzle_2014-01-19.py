#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2014/01/19/263785641/three-bs-bring-you-to-one

Name a famous person whose first and last names together contain four doubled letters -- all four of these being 
different letters of the alphabet. Who is it? For example, Buddy Holly's name has two doubled letters, D and L.
"""

def filter_d(d):
    count = 0
    tripwire = 0
    for k, v in d.iteritems():
        if k.isdigit():
            return False
        if v == 2:
            count = count + 1
        if k == '_' and v == 1:
            tripwire = 1
        if k.isalpha() == False and k != '_':
            return False
    if count == 4 and tripwire == 1:
        return True
    return False

def filter_d_two(d):
    for k, v in d.iteritems():
        if k.isdigit():
            return False
        if k == '_' and v != 1:
            return False
    return True 

def get_two_grams(word):
    to_return = []
    for i in range(len(word) - 1):
        to_return.append(word[i:i+2].lower())
    return to_return

def filter_two_grams(two_grams):
    fours = set()
    for t in two_grams:
        if t[0] == t[1] and t[0].isalpha():
            fours.add(t[0])
    if len(fours) == 4:
        return True
    return False 

def get_d(word):
    to_return = {}
    for char in word.lower():
        try:
            to_return[char] = to_return[char] + 1
        except:
            to_return[char] = 1
    return to_return 

def main():
    import sys
    fours = set() 
    words = open('enwiki-20131001-all-titles').readlines()
    #words = ['hello', 'world']
    for word in words:
        word = word[:-1]
        d = get_d(word)
        two_grams = get_two_grams(word)
        if filter_two_grams(two_grams) and filter_d_two(d):
             print word
           
if __name__ == "__main__":
    main()

