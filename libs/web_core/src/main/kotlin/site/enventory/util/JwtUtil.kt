package site.enventory.util

import io.github.cdimascio.dotenv.Dotenv
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import site.enventory.constant.JwtType
import java.util.Date
import javax.crypto.SecretKey

@Component
class JwtUtil(
    private val dotenv: Dotenv
) {

    private val jwtSecret: String = dotenv["JWT_SECRET"]
    private val key: SecretKey = Keys.hmacShaKeyFor(jwtSecret.toByteArray())

    private val accessTokenExpiration: Long = 1000 * 60 * 30
    private val refreshTokenExpiration: Long = 1000 * 60 * 60 * 24 * 7


    fun generateJwt(userId: String, jwtType: JwtType): String {
        val now = Date()
        val expiration = when (jwtType) {
            JwtType.ACCESS -> Date(now.time + accessTokenExpiration)
            JwtType.REFRESH -> Date(now.time + refreshTokenExpiration)
        }

        return Jwts.builder()
            .subject(userId)
            .issuedAt(now)
            .expiration(expiration)
            .signWith(key, Jwts.SIG.HS256)
            .compact()
    }

    fun getUserIdFromJwt(jwt: String): String {
        return try {
            val payload = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(jwt)
                .payload

            payload.subject
        } catch (e: Exception) {
            throw ResponseStatusException(
                HttpStatus.UNAUTHORIZED,
                "유효하지 않은 JWT입니다."
            )
        }
    }
}