plugins {
    `java-library`
    id("com.vanniktech.maven.publish") version "0.31.0"
}

repositories {
    mavenLocal()
    mavenCentral()
}

dependencies {
    implementation("com.grack:nanojson:1.9")
    testImplementation("ch.qos.logback:logback-classic:1.5.7")
    testImplementation("commons-io:commons-io:2.11.0")
    testImplementation("junit:junit:4.13.2")
    compileOnly("org.slf4j:slf4j-api:1.7.32")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}
