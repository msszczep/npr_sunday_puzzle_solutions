# Name a country with at least three consonants. These are the same consonants, in the same order, 
# as in the name of a language spoken by millions of people worldwide. The country and the place 
# where the language is principally spoken are in different parts of the globe. What country and 
# what language are these?

more countries_and_capitals.txt | tr '[:upper:]' '[:lower:]' | awk 'BEGIN {FS="-"} {print $1}' | sed 's/[aeiou ]//g' | awk '{if (length($0) > 2 ) {print $0} }' | grep krn
