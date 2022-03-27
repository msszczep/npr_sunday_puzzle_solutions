# Words starting with a "kw-" sound usually start with the letters QU-, 
# as in question, or "KW-," as in Kwanzaa. What common, uncapitalized 
# English word starting with a "kw-" sound contains none of the letters 
# Q, U, K, or W?

grep '  K W ' cmudict-0.7b.txt | awk '{print $1}' | grep -v Q | grep -v U | grep -v K | grep -v W
