dependencies {
    implementation(project(":common"))
    implementation(project(":web_core"))
    implementation(project(":postgres"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    // swagger
    implementation("org.springdoc:springdoc-openapi-starter-webflux-ui:2.6.0")
}