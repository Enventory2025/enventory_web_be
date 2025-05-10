package site.enventory.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import site.enventory.constant.ColorCode.*

@Component
class HttpLoggingFilter: WebFilter {

    private val logger = KotlinLogging.logger {}

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        val response = exchange.response

        logger.debug { "${GREEN.code}[REQ]${PURPLE.code}[${request.id}]${RESET.code} ${request.method} ${request.path}" }

        val now = System.currentTimeMillis()
        return chain.filter(exchange)
            .doOnSuccess { logger.debug { "${AQUA.code}[RES]${PURPLE.code}[${request.id}]${RESET.code} ${request.method} ${request.path} ${response.statusCode} ${AQUA.code}+${System.currentTimeMillis() - now}ms" } }
    }
}