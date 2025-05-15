plugins {
    id("java")
    id("org.springframework.boot") version "3.2.4"
    id("io.freefair.lombok") version "8.6"
}

apply(plugin = "io.spring.dependency-management")

group = "ru.startseva"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Lab3:DAO"))
    implementation(project(":Lab3:Service"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
}

tasks.test {
    useJUnitPlatform()
}