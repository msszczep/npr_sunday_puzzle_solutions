#!/usr/bin/python

"""
http://www.npr.org/2015/01/11/376352153/finding-the-pieces-to-form-a-new-nation

This week's challenge comes from listener Steve Baggish of Arlington, Mass. 
Think of a U.S. city whose name has nine letters. Remove three letters from the start 
of the name and three letters from the end. Only two will remain. How is this possible, and what city is it?
"""

rows = open('us_cities3.txt').readlines()
for row in rows:
    city = row[:-1].replace(' ', '')
    if len(city) == 9:
        print city, city[3:6]
