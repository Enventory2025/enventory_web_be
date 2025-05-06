package site.enventory.api

import io.github.cdimascio.dotenv.Dotenv
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class AppController(
    private val dotenv: Dotenv
) {

    private val serverEnv = dotenv["SERVER_ENV"] ?: "development"

    @GetMapping("/_health")
    fun healthCheck(): Mono<String> {
        return Mono.just("Enventory Web BE is running in $serverEnv environment")
    }
}