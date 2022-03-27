# Name a state that contains all five vowels — A, E, I, O, and U — once each.

more world-capitals.txt | awk 'BEGIN {FS=","} {print $1}' | grep -i a | grep -i e | grep -i i | grep -i o | grep -i u | grep -v ' '
