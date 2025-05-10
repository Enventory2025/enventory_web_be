package site.enventory.annotation


import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.core.MethodParameter
import org.springframework.http.HttpStatus
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono


@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Schema(hidden = true)
annotation class AccessToken

@Component
class AccessTokenArgumentResolver: HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(AccessToken::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any?> {
        return if (parameter.parameterType == String::class.java) {
            val token = exchange.request.headers.getFirst("Authorization")?.substringAfter("Bearer ")
            if (token == null) {
                throw ResponseStatusException(HttpStatus.FORBIDDEN, "인증 정보가 존재하지 않습니다.")
            } else {
                ReactiveSecurityContextHolder.getContext()
                    .map { token }
            }
        } else {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "@AccessToken 어노테이션은 String 타입의 파라미터에만 사용 가능합니다.")
        }
    }
}