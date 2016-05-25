#!/bin/sh
for i in "$@" 
do
	convert $i -flop $i
done
