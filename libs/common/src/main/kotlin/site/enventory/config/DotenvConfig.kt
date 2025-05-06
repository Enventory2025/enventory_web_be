package site.enventory.config

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class DotenvConfig {

    @Bean
    fun dotenv(): Dotenv {
        val rootDir = System.getProperty("user.dir")
        return Dotenv
            .configure()
            .directory(rootDir)
            .ignoreIfMissing()
            .ignoreIfMalformed()
            .load()
    }
}