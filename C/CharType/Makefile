# Makefile
# Sean Gordon
# skgordon #1405355
# CMPS 12M lab4
# 4/29/15
# Makefile for lab4 files: charType.c

FLAGS = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN = charType

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

check : $(EXEBIN)
	valgrind --leak-check=full $(EXEBIN) $(SOURCES) outfile
