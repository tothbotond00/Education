#!/bin/bash
FILE=$1

gcc "$FILE" -W -Wall -Wextra -pedantic -std=c99 -o todo
./todo 13:58 beadando 00:00 alvas 08:30 reggeli

RESULT="todolist.txt"
CONTENT=$(echo -e "13:58 - beadando\n00:00 - alvas\n08:30 - reggeli\n")
if [[ -f "$RESULT" && "$(cat "$RESULT")" == $CONTENT ]]; then
	echo "Correct!"
else
	echo "Wrong!"
fi
