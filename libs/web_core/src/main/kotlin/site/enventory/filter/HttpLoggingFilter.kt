package site.enventory.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import site.enventory.constant.ColorCode.*
import java.util.UUID.randomUUID

@Component
class HttpLoggingFilter: WebFilter {

    private val logger = KotlinLogging.logger {}

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        val response = exchange.response

        val requestId = randomUUID().toString().substring(0, 8)
        logger.debug { "${GREEN.code}[REQ]${PURPLE.code}[${requestId}]${RESET.code} ${request.method} ${request.path}" }

        val now = System.currentTimeMillis()
        return chain.filter(exchange)
            .doOnSuccess { logger.debug { "${AQUA.code}[RES]${PURPLE.code}[${requestId}]${RESET.code} ${request.method} ${request.path} ${response.statusCode} ${AQUA.code}+${System.currentTimeMillis() - now}ms" } }
    }
}