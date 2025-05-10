package site.enventory.annotation

import org.springframework.core.MethodParameter
import org.springframework.security.core.context.ReactiveSecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.reactive.BindingContext
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver
import org.springframework.web.server.ServerWebExchange
import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import reactor.core.publisher.Mono
import site.enventory.entity.UserEntity
import site.enventory.repository.UserRepository
import java.util.UUID


@Target(AnnotationTarget.VALUE_PARAMETER)
@Retention(AnnotationRetention.RUNTIME)
@Schema(hidden = true)
annotation class LoginMember

@Component
class LoginMemberArgumentResolver(
    private val userRepository: UserRepository
) : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.hasParameterAnnotation(LoginMember::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        bindingContext: BindingContext,
        exchange: ServerWebExchange
    ): Mono<Any?> {
        return if (parameter.parameterType == UserEntity::class.java) {
            ReactiveSecurityContextHolder.getContext()
                .map { it.authentication }
                .flatMap { authentication ->
                    val userId = authentication.principal as String
                    userRepository.findById(UUID.fromString(userId))
                        .switchIfEmpty(Mono.error(ResponseStatusException(HttpStatus.FORBIDDEN, "인증 정보가 유효하지 않습니다.")))
                }
        } else {
            throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "@LoginMember 어노테이션은 UserEntity 타입의 파라미터에만 사용 가능합니다.")
        }
    }
}