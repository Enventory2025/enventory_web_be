package site.enventory.api.envstore

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.enventory.annotation.LoginUser
import site.enventory.annotation.WrapResponse
import site.enventory.api.envstore.dto.request.CreateEnvStoreRequestDto
import site.enventory.api.envstore.dto.response.EnvStoreResponseDto
import site.enventory.constant.Uri
import site.enventory.entity.UserEntity

@RestController
@RequestMapping(Uri.ENV_STORES + Uri.V1)
@WrapResponse
@Tag(name = "EnvStore APIs", description = "환경변수 저장소 API")
class EnvStoreController(
    private val envStoreService: EnvStoreService
) {

    @Operation(
        summary = "환경변수 저장소 조회",
        description = """
            요청을 보낸 유저의 환경변수 저장소를 조회합니다.
        """
    )
    @GetMapping
    fun getAll(@LoginUser loginUser: UserEntity): Flux<EnvStoreResponseDto> {
        return envStoreService.getAll(loginUser)
    }

    @Operation(
        summary = "환경변수 저장소 등록",
        description = """
            요청을 보낸 유저의 환경변수 저장소를 등록합니다.
            환경변수 저장소 이름, 추가할 환경을 입력받습니다.
        """
    )
    @PostMapping
    fun create(
        @LoginUser loginUser: UserEntity,
        @RequestBody @Valid requestDto: CreateEnvStoreRequestDto
    ): Mono<EnvStoreResponseDto> {
        return envStoreService.create(loginUser, requestDto)
    }
}