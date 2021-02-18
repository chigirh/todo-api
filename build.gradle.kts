import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//kotlinDSL samplea
//https://github.com/gradle/kotlin-dsl-samples/tree/master/samples
plugins {
    id("org.springframework.boot") version "2.5.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.21-2"
    kotlin("plugin.spring") version "1.4.21-2"
    id("org.flywaydb.flyway") version "7.2.1"
    id("org.openapi.generator") version "5.0.1"
    id("org.hidetake.swagger.generator") version "2.18.2"
    id("com.thinkimi.gradle.MybatisGenerator") version "2.2"
}


group = "chigirh.app.todo.be"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
val generatedSourcesDir = "$buildDir/generated/openapi"

sourceSets {
    main {
        kotlin {
            sourceSets["main"].apply {
                kotlin.srcDir("$generatedSourcesDir/src/main/kotlin")
                kotlin.srcDir("$rootDir/src/gen/kotlin")
            }
        }
        resources {
            srcDir("$rootDir/src/gen/resources")
        }
    }
}

repositories {
    mavenCentral()
    maven { url = uri("https://repo.spring.io/milestone") }
    maven { url = uri("https://repo.spring.io/snapshot") }
}

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    //mybatis
    implementation("org.postgresql:postgresql:42.2.18")
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:2.1.4")
    implementation("org.mybatis.dynamic-sql:mybatis-dynamic-sql:1.1.4")

    implementation("javax.validation:validation-api:2.0.1.Final")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.jetbrains:annotations:20.1.0")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    implementation("org.apache.commons:commons-lang3:3.11")

    compile("io.springfox:springfox-swagger2:2.9.2")
    compile("io.springfox:springfox-swagger-ui:2.9.2")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}

buildscript {
    repositories {
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }
    dependencies {
        classpath("gradle.plugin.org.flywaydb:gradle-plugin-publishing:7.5.3")
        classpath("org.postgresql:postgresql:42.2.18")
        classpath("org.openapitools:openapi-generator-gradle-plugin:5.0.1")
        classpath("gradle.plugin.org.hidetake:gradle-swagger-generator-plugin:2.18.2")
    }
}
//db migration
apply(plugin = "org.flywaydb.flyway")

flyway {
    driver = "org.postgresql.Driver"
    url = "jdbc:postgresql://localhost:5432/todo_api"
    user = "root"
    password = "root"
}
//open api generator
val spec = "$rootDir/open-api/todo-api.yaml"

apply(plugin = "org.openapi.generator")

openApiGenerate {
    //config docs
    //https://openapi-generator.tech/docs/generators/kotlin-spring/
    //https://openapi-generator.tech/docs/configuration/
    generatorName.set("kotlin-spring")
    inputSpec.set(spec)
    outputDir.set(generatedSourcesDir)
    apiPackage.set("chigirh.app.todo.be.todoapi.oas3.controller")
    invokerPackage.set("chigirh.app.todo.be.todoapi")
    modelPackage.set("chigirh.app.todo.be.todoapi.oas3.model")

    configOptions.set(
        mapOf(
            "dateLibrary" to "java8"
        )
    )
    additionalProperties.set(
        mapOf(
            "delegatePattern" to "true",
            "unhandledException" to "true"
        )
    )
}
openApiValidate {
    inputSpec.set("$rootDir/tms-oas3/tms-backend.yaml")
}
//mybatis generator
apply(plugin = "com.thinkimi.gradle.MybatisGenerator")


mybatisGenerator {
    dependencies {
        mybatisGenerator("org.postgresql:postgresql:42.2.18")
        mybatisGenerator("org.mybatis.generator:mybatis-generator-core:1.4.0")
    }

    configFile = "$rootDir/src/main/resources/generatorConfig.xml"
    overwrite = true
}