# Makefile
# Sean Gordon
# skgordon #1405355
# pa4
# A Makefile for the Roots program
Roots: Roots.class
	echo Main-class: Roots > Manifest
	jar cvfm Roots Manifest Roots.class
	rm Manifest
	chmod u+x Roots

Roots.class: Roots.java
	javac -Xlint Roots.java

clean:
	rm -f Roots.class Roots

submit: Makefile Roots.java
	submit cmps011-pt.w15 pa4 Makefile Roots.java

check:
	ls /afs/cats.ucsc.edu/class/cmps011-pt.w15/pa4/skgordon
