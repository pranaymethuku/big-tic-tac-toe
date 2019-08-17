# java compiler
JC = javac
# target directory
OUTDIR = bin/
# compilation flags
JFLAGS = -g -d $(OUTDIR) -cp src/

.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS)  $*.java
# targeting all java files in subdirectories
CLASSES = ./**/*.java
default: classes
classes: $(CLASSES:.java=.class)
clean:
	rm -rf ./bin
