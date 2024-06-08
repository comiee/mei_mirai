import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    java
    kotlin("jvm") version "1.8.0"
}

group = "org.example"
version = "0.1.0"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    maven { url = uri("https://maven.aliyun.com/repositories/jcenter") }
    maven { url = uri("https://maven.aliyun.com/repositories/google") }
    maven { url = uri("https://maven.aliyun.com/repositories/central") }
    google()
    mavenCentral()
}

tasks.withType(KotlinJvmCompile::class.java) {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    // 开发时使用 mirai-core-api，运行时提供 mirai-core

    api("net.mamoe:mirai-core-api:${properties["version.mirai"]}")
    runtimeOnly("net.mamoe:mirai-core:${properties["version.mirai"]}")

    // 可以简单地只添加 api("net.mamoe:mirai-core:2.6.1")

    implementation("com.google.code.gson:gson:2.10.1")
}