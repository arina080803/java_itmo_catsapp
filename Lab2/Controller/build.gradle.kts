plugins {
    id("java")
}

group = "ru.startseva"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":Lab2:Service"))
    implementation(project(":Lab2:DAO"))
    implementation("org.hibernate.orm:hibernate-core:6.4.4.Final")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter:5.10.0")
    testImplementation("org.mockito:mockito-core:5.11.0")
}

tasks.test {
    useJUnitPlatform()
}