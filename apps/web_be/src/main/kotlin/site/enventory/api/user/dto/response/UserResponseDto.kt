package site.enventory.api.user.dto.response

import io.swagger.v3.oas.annotations.media.Schema
import org.springframework.http.HttpStatus
import org.springframework.web.server.ResponseStatusException
import site.enventory.constant.Provider
import site.enventory.entity.UserEntity
import java.time.Instant
import java.util.UUID

@Schema(description = "유저 정보 응답 DTO")
data class UserResponseDto(
    @Schema(required = true, description = "유저 ID")
    val id: UUID,

    @Schema(required = true, description = "유저 이메일")
    val email: String,

    @Schema(required = true, description = "이메일 제공자 (ex. GOOGLE, GITHUB)")
    val provider: Provider,

    @Schema(required = true, description = "회원 가입 날짜")
    val createdAt: Instant,
) {
    companion object {
        fun of(user: UserEntity): UserResponseDto {
            return UserResponseDto(
                id = user.id ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "유저 ID가 존재하지 않습니다."),
                email = user.email,
                provider = user.provider,
                createdAt = user.createdAt ?: throw ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "유저 생성 날짜가 존재하지 않습니다."),
            )
        }
    }
}