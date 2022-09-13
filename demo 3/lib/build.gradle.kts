import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	//id("org.springframework.boot") version "2.7.3"
	//id("io.spring.dependency-management") version "1.0.13.RELEASE"

	kotlin("jvm") version "1.6.21"
	kotlin("plugin.spring") version "1.6.21"
	id("idea")
	id("kotlin")
	id("kotlin-kapt")
	id("kotlin-allopen")
}

group "hsog.lib"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.7.0")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}
