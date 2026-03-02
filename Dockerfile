FROM eclipse-temurin:21-jdk

WORKDIR /

COPY /app .
COPY /gradle .
COPY gradle.properties .
COPY settings.gradle.kts .
COPY gradlew .

RUN ./gradlew installDist

CMD ./build/install/app/bin/app