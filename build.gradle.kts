buildscript {
    val springBootVersion = "1.5.3.RELEASE"
    val kotlinVersion = "1.1.0"

    extra["kotlinVersion"] = kotlinVersion

    repositories {
        mavenCentral()
    }

    dependencies {
        classpath("org.springframework.boot:spring-boot-gradle-plugin:$springBootVersion")
        classpath("org.jetbrains.kotlin:kotlin-noarg:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-allopen:$kotlinVersion")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion")
    }
}

apply {
    plugin("kotlin")
    plugin("kotlin-spring")
    plugin("kotlin-jpa")
    plugin("org.springframework.boot")
}

version = "0.0.1-SNAPSHOT"

configure<JavaPluginConvention> {
    setSourceCompatibility(1.8)
    setTargetCompatibility(1.8)
}

repositories {
    mavenCentral()
    maven { setUrl("https://dl.bintray.com/kotlin/exposed") }
}

val kotlinVersion = extra["kotlinVersion"] as String

dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-data-jpa")
    compile("com.h2database:h2")
    compile("org.jetbrains.kotlin:kotlin-stdlib:$kotlinVersion")
    compile("org.jetbrains.kotlin:kotlin-reflect:$kotlinVersion")
    compile("com.fasterxml.jackson.module:jackson-module-kotlin:2.8.4")
    compile(exposedModule("exposed"))
    compile(exposedModule("spring-transaction"))
    compile("org.postgresql:postgresql:9.4.1208")

    testCompile("org.springframework.boot:spring-boot-starter-test")
}

fun exposedModule(module: String) = "org.jetbrains.exposed:$module:0.5.0"
