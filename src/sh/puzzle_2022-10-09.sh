# Name two things that many houses are built with: "[blank] and [blank]." Drop the first letter of the first thing. Change the last two letters of the second thing to a "Y." And you'll name a popular TV show, "[blank] and [blank]." What show is it?

time egrep '^[A-Za-z]+_&_[A-Za-z]+y$' enwiki-20200820-all-titles-clean.txt > output_2022-10-09.txt
