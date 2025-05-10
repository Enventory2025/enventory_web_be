package site.enventory.api.envstore

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import site.enventory.annotation.LoginUser
import site.enventory.annotation.WrapResponse
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
}