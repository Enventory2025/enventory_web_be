package site.enventory.constant

// 로깅에 쓰일 컬러 코드
enum class ColorCode(val code: String) {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    YELLOW("\u001B[33m"),
    BLUE("\u001B[34m"),
    PURPLE("\u001B[35m"),
    AQUA("\u001B[36m"),
    GRAY("\u001B[37m"),
}