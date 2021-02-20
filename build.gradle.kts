import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//kotlinDSL samplea
//https://github.com/gradle/kotlin-dsl-samples/tree/master/samples
plugins {
    id("java")
    id("org.springframework.boot") version "2.5.0-SNAPSHOT"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"
    kotlin("jvm") version "1.4.21-2"
    kotlin("plugin.spring") version "1.4.21-2"
    id("org.flywaydb.flyway") version "7.2.1"
    id("org.openapi.generator") version "5.0.1"
    id("org.hidetake.swagger.generator") version "2.18.2"
    id("com.thinkimi.gradle.MybatisGenerator") version "2.2"
    id("com.google.protobuf") version "0.8.15"
}


group = "chigirh.app.todo.be"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}
val generatedOpenApiSourcesDir = "$buildDir/generated/openapi"
val generatedGrpcSourcesDir = "$buildDir/generated/source"

sourceSets {
    main {
        kotlin {
            sourceSets["main"].apply {
                kotlin.srcDir("$generatedOpenApiSourcesDir/src/main/kotlin")
                kotlin.srcDir("$rootDir/src/gen/kotlin")
            }
        }
        java {
            sourceSets["main"].apply {
                java.srcDir("$generatedGrpcSourcesDir/proto/main/java")
                java.srcDir("$generatedGrpcSourcesDir/proto/main/grpc")
                java.srcDir("$rootDir/src/main/proto")
            }
        }
        proto {
            sourceSets["main"].apply {
                java.srcDir("$rootDir/src/main/proto")
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
val swaggerVersion = "2.9.2"
val grpcVersion = "1.35.0"

dependencies {
    //spring
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter-aop")
    implementation("io.github.lognet:grpc-spring-boot-starter:4.4.4")
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
    //Swagger
    compile("io.springfox:springfox-swagger2:${swaggerVersion}")
    compile("io.springfox:springfox-swagger-ui:${swaggerVersion}")
    //gRPC
    compile("io.grpc:grpc-netty-shaded:${grpcVersion}")
    compile("io.grpc:grpc-protobuf:${grpcVersion}")
    compile("io.grpc:grpc-stub:${grpcVersion}")
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
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.8")
    }
}

springBoot {
    mainClass.set("chigirh.app.todo.be.TodoApplicationKt")
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
    outputDir.set(generatedOpenApiSourcesDir)
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
//gRPC
apply(plugin = "com.google.protobuf")
//https://github.com/saturnism/grpc-by-example-java
protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.7.1"
    }
    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}
