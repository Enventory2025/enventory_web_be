package site.enventory.api.envstore

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import site.enventory.annotation.WrapResponse
import site.enventory.constant.Uri

@RestController
@RequestMapping(Uri.ENV_STORES + Uri.V1)
@WrapResponse
@Tag(name = "EnvStore APIs", description = "환경변수 저장소 API")
class EnvStoreController {

    @Operation(
        summary = "환경변수 저장소 조회",
        description = """
            요청을 보낸 유저의 환경변수 저장소를 조회합니다.
        """
    )

}