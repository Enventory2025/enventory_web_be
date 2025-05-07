package site.enventory.constant

// 로깅에 쓰일 컬러 코드
enum class ColorCode(val code: String) {
    RESET("\u001B[0m"),
    GREEN("\u001B[32m"),
    AQUA("\u001B[36m"),
    PURPLE("\u001B[35m"),
    YELLOW("\u001B[33m"),
}