import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

group = "com.apw.traksim2"
version = "1.0-SNAPSHOT"

buildscript {
    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    var kotlinVersion: String by extra
    @Suppress("ASSIGNED_BUT_NEVER_ACCESSED_VARIABLE")
    var lwjglVersion: String by extra

    @Suppress("UNUSED_VALUE")
    kotlinVersion = "1.2.51"
    @Suppress("UNUSED_VALUE")
    lwjglVersion = "3.0.0b"
}

val kotlinVersion: String by extra
val lwjglVersion: String by extra


plugins {
    kotlin("jvm") version "1.2.51"
    application
}

repositories {
    mavenCentral()
}

dependencies {
    compile(kotlin("stdlib-jdk8", kotlinVersion))
    compile("org.lwjgl:lwjgl:$lwjglVersion")
    compile("org.lwjgl:lwjgl-platform:$lwjglVersion:natives-windows")
    compile("org.lwjgl:lwjgl-platform:$lwjglVersion:natives-linux")
    compile("org.lwjgl:lwjgl-platform:$lwjglVersion:natives-osx")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClassName = "sim/TrakSim.kt"
}


