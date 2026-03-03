.PHONY: clean test reload-classes install lint setup build

clean:
	./gradlew clean

test:
	./gradlew test

reload-classes:
	./gradlew -t classes

install:
	./gradlew installDist

lint:
	./gradlew checkstyleMain checkstyleTest

setup:
	chmod +x gradlew
	./gradlew clean build -x test

build:
	./gradlew build

run:
	./gradlew bootRun

check:
	./gradlew check