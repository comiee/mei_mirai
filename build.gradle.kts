import org.jetbrains.kotlin.gradle.tasks.KotlinJvmCompile

plugins {
    java
    kotlin("jvm") version "1.8.0"
}

group = "com.comiee.mei"
version = "0.1.0"

repositories {
    maven { url = uri("https://maven.aliyun.com/repository/public/") }
    maven { url = uri("https://maven.aliyun.com/repositories/jcenter") }
    maven { url = uri("https://maven.aliyun.com/repositories/google") }
    maven { url = uri("https://maven.aliyun.com/repositories/central") }
    google()
    mavenCentral()
}

@Suppress("UnstableApiUsage")
java {
    // 配置Java编译选项
    sourceCompatibility = JavaVersion.VERSION_20
    targetCompatibility = JavaVersion.VERSION_20
}

tasks.withType<JavaCompile> {
    options.compilerArgs.add("--enable-preview")
    options.compilerArgs.add("-Xlint:preview")
    options.encoding = "utf-8"
}

tasks.withType(KotlinJvmCompile::class.java) {
    kotlinOptions.jvmTarget = "1.8"
}

dependencies {
    // 开发时使用 mirai-core-api，运行时提供 mirai-core

    api("net.mamoe:mirai-core-api:${properties["version.mirai"]}")
    runtimeOnly("net.mamoe:mirai-core:${properties["version.mirai"]}")

    implementation("com.google.code.gson:gson:2.10.1")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")
    testImplementation("org.junit.platform:junit-platform-suite-api:1.7.2")
    testRuntimeOnly("org.junit.platform:junit-platform-suite-engine:1.7.2")
}

// 打包jar步骤：
//   1.文件->项目结构->工件->+->jar->来自具有依赖项的模块  META-INF/MANIFEST.MF的目录选src下
//   2.构建->构建工件
//   3.打开jar包，将META-INF下的.SF文件全部删掉
//   4.运行的时候记得用java -jar