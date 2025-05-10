package site.enventory.store

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import site.enventory.cache.JwtCache

@Repository
class JwtCacheStore(
    private val redisTemplate: ReactiveRedisTemplate<String, String>,
    private val objectMapper: ObjectMapper
) {

    private val prefix = "jwt:"

    fun save(jwtCache: JwtCache): Mono<JwtCache> {
        return redisTemplate.opsForValue()
            .set("$prefix${jwtCache.userId}", objectMapper.writeValueAsString(jwtCache))
            .map { jwtCache }
    }

    fun findByUserId(userId: String): Mono<JwtCache> {
        return redisTemplate.opsForValue()
            .get("$prefix$userId")
            .map { objectMapper.readValue(it, JwtCache::class.java) }
    }

    fun deleteByUserId(userId: String): Mono<Boolean> {
        return redisTemplate.opsForValue()
            .delete("$prefix$userId")
    }
}