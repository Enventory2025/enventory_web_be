rootProject.name = "enventory_web_be"

listOf(
    "common",
    "web_core"
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/libs/$it")
}

listOf(
    "web_be",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/apps/$it")
}

listOf(
    "postgres",
).forEach {
    include(it)
    project(":$it").projectDir = File("$rootDir/libs/infra/$it")
}