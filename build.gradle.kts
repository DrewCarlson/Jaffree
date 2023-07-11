plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    api("com.grack:nanojson:1.7")
    testImplementation("ch.qos.logback:logback-classic:1.2.10")
    testImplementation("commons-io:commons-io:2.11.0")
    testImplementation("junit:junit:4.13.2")
    compileOnly("org.slf4j:slf4j-api:1.7.32")
}

group = "com.github.kokorin.jaffree"
version = "0"
description = "Jaffree"

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

publishing {
    publications.create<MavenPublication>("maven") {
        from(components["java"])
    }
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
    options.encoding = "UTF-8"
}
