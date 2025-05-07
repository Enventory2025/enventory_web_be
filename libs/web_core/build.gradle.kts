dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-aop")

    // kotlin logging
    implementation("io.github.oshai:kotlin-logging-jvm:5.1.0")
//    // jwt
//    implementation("io.jsonwebtoken:jjwt-api:0.12.5")
//    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.12.5")
//    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.12.5")

//    // swagger
//    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
}