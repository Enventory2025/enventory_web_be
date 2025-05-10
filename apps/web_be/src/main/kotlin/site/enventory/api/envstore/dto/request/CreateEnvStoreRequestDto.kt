package site.enventory.api.envstore.dto.request

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotEmpty

@Schema(description = "환경변수 저장소 생성 요청 DTO")
data class CreateEnvStoreRequestDto(
    @Schema(required = true, description = "환경변수 저장소 이름")
    @field:NotBlank
    val name: String,

    @Schema(required = true, description = "추가할 환경 목록")
    @field:NotEmpty
    val environments: List<String>,
)
