package site.enventory.api

import io.github.cdimascio.dotenv.Dotenv
import io.swagger.v3.oas.annotations.Hidden
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import site.enventory.constant.Uri

@Hidden
@RestController
class AppController(
    private val dotenv: Dotenv
) {

    private val serverEnv = dotenv["SERVER_ENV"] ?: "development"

    @GetMapping(Uri.HEALTH)
    fun healthCheck(): Mono<String> {
        return Mono.just("Enventory Web BE is running in $serverEnv environment")
    }
}