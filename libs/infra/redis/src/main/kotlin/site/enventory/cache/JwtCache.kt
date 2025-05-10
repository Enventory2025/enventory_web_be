package site.enventory.cache

data class JwtCache(
    val userId: String,
    val refreshToken: String
)