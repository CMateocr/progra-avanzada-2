plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.prog_avanzada"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.hibernate:hibernate-core:7.1.10.Final")
    implementation("org.postgresql:postgresql:42.7.8")
}

tasks.test {
    useJUnitPlatform()
}