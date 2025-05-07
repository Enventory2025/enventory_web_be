package site.enventory.aspect

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.enventory.response.EnventoryResponse

@Aspect
@Component
class ResponseWrappingAspect {

    @Around("@annotation(site.enventory.annotation.WrapResponse) || @within(site.enventory.annotation.WrapResponse)")
    fun wrapResponse(joinPoint: ProceedingJoinPoint): Any? {
        val result = joinPoint.proceed()

        return when (result) {
            is Mono<*> -> result.map { wrapIfNeeded(it) }
            is Flux<*> -> result.collectList().map { wrapIfNeeded(it) }
            else -> wrapIfNeeded(result)
        }
    }

    private fun wrapIfNeeded(value: Any?): Any {
        return value as? EnventoryResponse<*> ?: EnventoryResponse(data = value)
    }
}
