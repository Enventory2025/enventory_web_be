dependencies {
    implementation(project(":common"))
    implementation(project(":web_core"))
    implementation(project(":postgres"))

    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("org.springframework.boot:spring-boot-starter-validation")

    implementation("io.github.cdimascio:dotenv-kotlin:6.4.1")
}