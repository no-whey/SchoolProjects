#------------------------------------------------------------------------------
# Makefile
# Sean Gordon
# skgordon #1405355
# CMPS 12B pa2
# 4/17/15
# A Makefile for Search.java
#------------------------------------------------------------------------------

JAVASRC   = Search.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = Search
CLASSES   = Search.class
JARFILE   = Search
SUBMIT    = submit cmps012b-pt.s15 pa2

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest
	chmod +x $(JARFILE)

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)

submit: $(SOURCES)
	$(SUBMIT) $(SOURCES)
