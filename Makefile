all:
	javac *.java
	java Main
	rm *.class

clean:
	rm *.class

c:
	javac *.java

wc:
	wc -l *.java | tail -n 1
