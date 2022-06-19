# The name of what country contains a deodorant and an air freshener in consecutive letters?

more world-capitals.txt | awk 'BEGIN {FS=","} {print $1}' | grep -i glade
