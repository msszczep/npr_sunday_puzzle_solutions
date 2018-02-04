#!/usr/bin/python
# -*- coding: utf-8 -*-

"""
http://www.npr.org/2014/08/17/340944712/is-there-an-echo-in-here

You have a target with six rings, bearing the numbers 16, 17, 23, 24, 39, and 40. 
How can you score exactly 100 points, by shooting at the target.
"""

def main():
    r = range(10)
    for a in r:
        for b in r:
            for c in r:
                for d in r:
                    for e in r:
                        for f in r:
                            if (((a * 16) + (b * 17) + (c * 23) + (d * 24) + (e * 39) + (f * 40)) == 100):
                               print a, b, c, d, e, f 

if __name__ == "__main__":
        main()
