plugins {
    java
    application
}

group = "org.example.tetris"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks {
    test {
        useJUnitPlatform()
    }

    jar {
        manifest {
            attributes["Main-Class"] = "org.example.tetris.TetrisMain"
        }
        // Including all dependencies in the JAR for portability
        from(configurations.runtimeClasspath.get().map { if (it.isDirectory) it else zipTree(it) })
        duplicatesStrategy = DuplicatesStrategy.EXCLUDE
    }

    application {
        mainClass.set("org.example.tetris.TetrisMain")
    }
}
