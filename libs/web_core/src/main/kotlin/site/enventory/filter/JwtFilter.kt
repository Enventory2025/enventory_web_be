package site.enventory.filter

import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.util.AntPathMatcher
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import org.springframework.web.server.WebFilter
import org.springframework.web.server.WebFilterChain
import reactor.core.publisher.Mono
import site.enventory.constant.Uri
import site.enventory.util.JwtUtil

@Component
class JwtFilter(
    private val jwtUtil: JwtUtil
): WebFilter {

    override fun filter(exchange: ServerWebExchange, chain: WebFilterChain): Mono<Void> {
        val path = exchange.request.path.pathWithinApplication().value()
        val method = exchange.request.method

        val skip = when (method) {
            HttpMethod.GET -> Uri.GET_WHITELIST.any { pattern ->
                AntPathMatcher().match(pattern, path)
            }
            HttpMethod.POST -> Uri.POST_WHITELIST.any { pattern ->
                AntPathMatcher().match(pattern, path)
            }
            else -> false
        }

        if (skip) {
            return chain.filter(exchange)
        }

        val jwt = exchange.request.headers["Authorization"]?.firstOrNull()
            ?.replace("Bearer ", "")
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "인증 정보가 존재하지 않습니다.")

        return try {
            val userId = jwtUtil.getUserIdFromJwt(jwt)
            val authentication = UsernamePasswordAuthenticationToken(userId, null, emptyList())
            chain.filter(exchange)
                .contextWrite(ReactiveSecurityContextHolder.withAuthentication(authentication))
        } catch (e: ResponseStatusException) {
            throw ResponseStatusException(HttpStatus.FORBIDDEN, "인증 정보가 유효하지 않습니다.")
        }
    }
}