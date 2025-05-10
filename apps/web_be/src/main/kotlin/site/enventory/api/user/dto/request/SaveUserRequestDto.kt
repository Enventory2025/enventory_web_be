package site.enventory.api.user.dto.request

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import site.enventory.constant.Provider

data class SaveUserRequestDto(
    @field:Email
    @field:NotNull
    @field:NotBlank
    val email: String,

    @field:NotNull
    val provider: Provider,

    @field:NotNull
    @field:NotBlank
    val providerId: String,
)
