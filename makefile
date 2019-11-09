build:
	mkdir -p bin
	javac -d bin -cp src:res:lib/common/*:lib/x86/* src/Main.java

run:
	java -Djava.library.path=sys/x86 -cp bin:res:lib/common/*:lib/x86/* Main

run-arm:
	java -Djava.library.path=sys/arm -cp bin:res:lib/common/*:lib/arm/* Main

archive:
	$(eval NAME := $(shell basename $(CURDIR)))
	mkdir -p zip
	mkdir -p zip/x86
	mkdir -p zip/arm
	jar cfm zip/x86/$(NAME).jar .mf -C bin . -C res .
	cp zip/x86/$(NAME).jar zip/arm/$(NAME).jar
	cp sys/x86/* zip/x86
	cp sys/arm/* zip/arm
	cp lib/common/* zip/x86
	cp lib/common/* zip/arm
	cp lib/arm/* zip/arm
	cp lib/x86/* zip/x86
	echo "#/bin/sh\njava -Djava.library.path=. -jar $(NAME).jar" > zip/x86/$(NAME).sh
	cp zip/x86/$(NAME).sh zip/arm/$(NAME).sh
	echo "java -Djava.library.path=. -jar $(NAME).jar" > zip/x86/$(NAME).bat
	chmod u+x zip/x86/$(NAME).sh
	chmod u+x zip/arm/$(NAME).sh
	chmod u+x zip/x86/$(NAME).bat
	zip $(NAME)-x86.zip zip/x86/*
	zip $(NAME)-arm.zip zip/arm/*

exec:
	$(eval NAME := $(shell basename $(CURDIR)))
	java -Djava.library.path=zip/x86 -jar zip/x86/$(NAME).jar

exec-arm:
	$(eval NAME := $(shell basename $(CURDIR)))
	java -Djava.library.path=zip/arm -jar zip/arm/$(NAME).jar

clean:
	rm -r -f bin/*
	rm -r -f zip/*

.PHONY: build run run-arm archive exec exec-arm clean
