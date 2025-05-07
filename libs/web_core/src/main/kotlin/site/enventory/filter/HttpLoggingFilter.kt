package site.enventory.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono

@Component
class HttpLoggingFilter: WebFilter {

    val logger = KotlinLogging.logger {}

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val request = exchange.request
        logger.debug { "[REQ][${request.path}] 요청 수신" }
        val now = System.currentTimeMillis()
        return chain.filter(exchange)
            .doOnSuccess { logger.debug { "[RES] 응답 완료 +${System.currentTimeMillis() - now}ms"} }
    }
}