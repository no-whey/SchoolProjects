#------------------------------------------------------------------------------
# Makefile
# Sean Gordon
# skgordon #1405355
# CMPS 12B pa1
# 4/5/15
# A Makefile for pa1 (Extrema.java, Makefile, README)
#------------------------------------------------------------------------------

JAVASRC   = Extrema.java
SOURCES   = README Makefile $(JAVASRC)
MAINCLASS = Extrema
CLASSES   = Extrema.class
JARFILE   = Extrema
SUBMIT    = submit cmps012b-pt.s15 pa1

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
