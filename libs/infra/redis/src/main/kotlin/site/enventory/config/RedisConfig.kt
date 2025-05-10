package site.enventory.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory
import org.springframework.data.redis.connection.RedisPassword
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    private val dotenv: Dotenv
) {

    private val redisHost: String = dotenv["REDIS_HOST"]
    private val redisPort: Int = dotenv["REDIS_PORT"].toInt()
    private val redisPassword: String = dotenv["REDIS_PASSWORD"]

    @Bean
    @Primary
    fun reactiveRedisconnectionFactory(): ReactiveRedisConnectionFactory {
        val redisStandaloneConfiguration = RedisStandaloneConfiguration(
            redisHost,
            redisPort
        )
        redisStandaloneConfiguration.database = 0
        redisStandaloneConfiguration.password = RedisPassword.of(redisPassword)
        return LettuceConnectionFactory(redisStandaloneConfiguration)
    }

    @Bean
    @Primary
    fun reactiveRedisTemplate(factory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> {
        val serializer = StringRedisSerializer()
        val jackson2JsonRedisSerializer = Jackson2JsonRedisSerializer(String::class.java)
        val serializationContext = RedisSerializationContext.newSerializationContext<String, String>()
            .key(serializer)
            .value(jackson2JsonRedisSerializer)
            .hashKey(serializer)
            .hashValue(jackson2JsonRedisSerializer)
            .build()

        return ReactiveRedisTemplate(factory, serializationContext)
    }
}