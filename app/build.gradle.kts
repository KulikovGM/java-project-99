plugins {
    application
    //checkstyle
    jacoco
    id("io.freefair.lombok") version "8.13.1"
    //id("org.sonarqube") version "6.2.0.5505"
    id("io.sentry.jvm.gradle") version "5.8.0"
    id("org.springframework.boot") version "3.2.2"
    id("io.spring.dependency-management") version "1.1.3"
}

group = "hexlet.code"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(libs.guava)

    implementation(libs.springBootStarterDataJpa)
    implementation(libs.springBootStarterWeb)
    implementation(libs.springBootStarterDataJpa)
    implementation(libs.springBootStarterValidation)
    implementation(libs.springBootStarterActuator)
    implementation(libs.springBootStarterSecurity)
    implementation(libs.springBootDevtools)
    implementation(libs.springBootConfigProcessor)
    implementation("org.springframework.boot:spring-boot-starter-oauth2-resource-server")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("net.datafaker:datafaker:2.3.0")

    testImplementation(libs.springBootStarterTest)
    testImplementation(libs.springBootStarterWebTest)
    testImplementation("org.springframework.security:spring-security-test")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    testImplementation(platform("org.junit:junit-bom:5.11.2"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.11.1")
    testImplementation("net.javacrumbs.json-unit:json-unit-assertj:3.2.7")

    implementation("com.h2database:h2")
}

testing {
    suites {
        val test by getting(JvmTestSuite::class) {
            useJUnitJupiter("5.10.1")
        }
    }
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

application {
    mainClass = "hexlet.code.AppApplication"
}
