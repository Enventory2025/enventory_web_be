import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":common"))

    implementation("org.springframework.boot:spring-boot-starter-data-r2dbc")
    implementation("io.r2dbc:r2dbc-postgresql:0.8.13.RELEASE")
    implementation("io.r2dbc:r2dbc-pool")
    implementation("io.r2dbc:r2dbc-spi")

    implementation("com.github.f4b6a3:uuid-creator:6.1.1")
}