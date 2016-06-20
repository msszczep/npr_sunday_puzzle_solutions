# http://www.npr.org/2016/06/12/481702569/this-puzzle-packs-syllables-into-small-spaces

# Take the word baci (Italian for "kisses"). You can rearrange the letters to "I C A B" — 
# which sounds like a sentence, "I see a bee."

# Now, think of a unit of measurement. Rearrange its letters and read them out loud to 
# form a sentence complimenting someone on their appearance. What's the word, and what's the sentence?

more data_cleaned.noun | awk '{if ($2 == '23') { print $5} }' | egrep '^[abcgicutjklmnopqry]+$' | sort | uniq | egrep '[ura]+'
