#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2014/01/26/266210037/take-synonyms-for-a-spin-or-pirouette

 What word, containing two consecutive S's, becomes its own synonym if you drop those S's?
"""

def main():
	ss = set()
	all_words = set()
        words = open('ni2.txt').readlines()
        for word in words:
		word = word[:-1]
		all_words.add(word)
		if word.lower().find('ss') > -1:
			ss.add(word)
	# print len(ss)
	for ss_word in ss:
		ss_removed = ss_word.replace('ss', '')
		if ss_removed in all_words:
			print ss_word, ss_removed

if __name__ == "__main__":
        main()
