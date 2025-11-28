plugins {
    id("java")
    id("io.freefair.lombok") version "9.1.0"
}

group = "com.programacion_avanzada"
version = "unspecified"

repositories {
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation("org.hibernate:hibernate-core:7.1.10.Final")

    implementation("org.postgresql:postgresql:42.7.8")

    // ! LOMBOK
//    compileOnly("org.projectlombok:lombok:1.18.36")
//    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}