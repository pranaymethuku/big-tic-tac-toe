# java compiler
JC = javac
# target directory
OUTDIR = bin/
# source directory
SRCDIR = src/
# compilation flags
JFLAGS = -g -d $(OUTDIR) -cp $(SRCDIR)/

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS)  $*.java
# targeting all java files in subdirectories
CLASSES = ./**/*.java
default: classes
classes: $(CLASSES:.java=.class)
clean:
	rm -rf ./bin
