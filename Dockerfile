FROM eclipse-temurin:21-jdk

WORKDIR /java-project-99

COPY /java-project-99 .


RUN ./gradlew installDist

CMD ./build/install/app/bin/app