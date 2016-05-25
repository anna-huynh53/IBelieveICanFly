#!/bin/sh
for i in "$@" 
do
	convert $i -resize 20x20 $i
done
