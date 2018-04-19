all: src
	mkdir -p bin
	javac -d bin -cp src:lib/* src/Main.java

clean:
	rm -r -f bin/*

run: bin
	java -cp bin:lib/* -Djava.library.path=natives Main
