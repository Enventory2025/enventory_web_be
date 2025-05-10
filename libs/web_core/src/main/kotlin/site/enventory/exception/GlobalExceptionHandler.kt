package site.enventory.exception

import io.github.oshai.kotlinlogging.KotlinLogging
import org.aspectj.apache.bcel.classfile.Unknown
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler
import org.springframework.core.annotation.Order
import org.springframework.core.io.buffer.DataBufferFactory
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.server.ServerWebExchange
import reactor.core.publisher.Mono
import site.enventory.constant.ColorCode
import site.enventory.constant.ColorCode.*
import site.enventory.response.EnventoryResponse
import java.nio.charset.StandardCharsets

@Component
@Order(-2) // Spring의 기본 에러 핸들러보다 우선순위를 높게 설정
class GlobalExceptionHandler : ErrorWebExceptionHandler {

    private val logger = KotlinLogging.logger { }

    override fun handle(exchange: ServerWebExchange, ex: Throwable): Mono<Void> {
        val requestId = exchange.request.id
        val path = exchange.request.path
        val method = exchange.request.method
        val response = exchange.response
        val bufferFactory: DataBufferFactory = response.bufferFactory()

        val httpStatus = when (ex) {
            is ResponseStatusException -> ex.statusCode
            else -> HttpStatus.INTERNAL_SERVER_ERROR
        }

        response.statusCode = httpStatus
        response.headers.contentType = MediaType.APPLICATION_JSON

        val message = ex.message?.replace("\"", "'") ?: "Unexpected Error"
        val errorResponse = EnventoryResponse<Unknown>(
            message = message,
        )


        val dataBuffer = bufferFactory.wrap(errorResponse.toString().toByteArray(StandardCharsets.UTF_8))
        logger.error {
            buildString {
                appendLine("${RED.code}[ERROR]${PURPLE.code}[${requestId}]${RESET.code} $method $path ${GRAY.code}$message${RESET.code}")
                append(
                    ex.stackTraceToString()
                        .lines()
                        .joinToString("\n") { "    $it" }
                )
            }
        }

        return response.writeWith(Mono.just(dataBuffer))
    }
}
