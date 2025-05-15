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
    implementation(project(":Lab3:Service"))
    implementation(project(":Lab3:DAO"))
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    implementation("junit:junit:4.13.1")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-test:3.2.2")
    implementation("org.springframework.boot:spring-boot-test-autoconfigure:3.2.2")
    implementation("org.springframework:spring-test:6.1.3")
    // https://mvnrepository.com/artifact/com.h2database/h2
    testImplementation("com.h2database:h2:1.3.148")
    implementation("org.springframework.boot:spring-boot-starter-security")
    testImplementation("org.springframework.security:spring-security-test:6.2.4")

}

tasks.test {
    useJUnitPlatform()
}