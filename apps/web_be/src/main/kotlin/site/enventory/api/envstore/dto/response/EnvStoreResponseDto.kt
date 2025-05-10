package site.enventory.api.envstore.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import site.enventory.entity.EnvStoreEntity
import java.time.Instant
import java.util.UUID

@Schema(description = "환경변수 저장소 응답 DTO")
data class EnvStoreResponseDto(
    @Schema(description = "환경변수 저장소 ID")
    val id: UUID,

    @Schema(description = "환경변수 저장소 이름")
    val name: String,

    @Schema(description = "환경변수 저장소 생성 일자")
    val createdAt: Instant,
) {
    companion object {
        fun of(envStoreEntity: EnvStoreEntity): EnvStoreResponseDto {
            return EnvStoreResponseDto(
                id = envStoreEntity.id ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "환경변수 저장소 ID가 존재하지 않습니다."),
                name = envStoreEntity.name,
                createdAt = envStoreEntity.createdAt,
            )
        }
    }
}
