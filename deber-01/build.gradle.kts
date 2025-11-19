plugins {
    id("java")
}

group = "com.programacion_avanzada"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jboss.weld.se:weld-se-core:6.0.3.Final")
    implementation("io.smallrye:jandex:3.5.1")

    // https://mvnrepository.com/artifact/io.helidon.dbclient/helidon-dbclient-jdbc
    implementation("io.helidon.dbclient:helidon-dbclient-jdbc:4.3.2")

    implementation("io.helidon.dbclient:helidon-dbclient:4.3.2")

    // https://mvnrepository.com/artifact/io.helidon.dbclient/helidon-dbclient-hikari
    implementation("io.helidon.dbclient:helidon-dbclient-hikari:4.3.2")

    implementation("io.helidon.config:helidon-config-yaml:4.3.2")

    implementation("org.postgresql:postgresql:42.7.8")

    // ! LOMBOK
    compileOnly("org.projectlombok:lombok:1.18.36")
    annotationProcessor("org.projectlombok:lombok:1.18.36")
}

tasks.test {
    useJUnitPlatform()
}

sourceSets {
    main {
        output.setResourcesDir(file("${buildDir}/classes/java/main"))
    }
}