import org.springframework.boot.gradle.tasks.bundling.BootJar

val jar: Jar by tasks
val bootJar: BootJar by tasks

bootJar.enabled = false
jar.enabled = true

dependencies {
    implementation(project(":common"))

    api("org.springframework.boot:spring-boot-starter-data-r2dbc")
    api("org.postgresql:r2dbc-postgresql:1.0.7.RELEASE")
    api("io.r2dbc:r2dbc-pool")
    api("io.r2dbc:r2dbc-spi")
    api("io.r2dbc:r2dbc-proxy")
}