rootProject.name = "enventory_web_be"

listOf(
    "common",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/libs/$it")
}