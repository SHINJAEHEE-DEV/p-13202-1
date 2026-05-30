
plugins {
    java
    id("org.springframework.boot") version "4.0.6"
    id("io.spring.dependency-management") version "1.1.7"
}

group = "com"
version = "0.0.1-SNAPSHOT"
description = "SpringTest2_Re"

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(21)
    }
}

repositories {
    mavenCentral()
}

dependencies {
    // 1. 기본 Spring Boot 웹/데이터/보안 의존성
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")

    // 2. 추가 유틸리티 라이브러리
    implementation("org.thymeleaf.extras:thymeleaf-extras-springsecurity6")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect")
    implementation("com.password4j:password4j:1.8.2")

    // 3. 롬복 및 개발 편의성 도구
    compileOnly("org.projectlombok:lombok")
    annotationProcessor("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")

    // 4. 데이터베이스 드라이버
    runtimeOnly("com.mysql:mysql-connector-j")

    // 5. 테스트 환경 (깔끔하게 통합됨)
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test") // 시큐리티 테스트용
    testCompileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
}

tasks.withType<Test> {
    useJUnitPlatform()
}
