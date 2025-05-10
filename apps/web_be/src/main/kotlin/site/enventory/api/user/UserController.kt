package site.enventory.api.user

import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import site.enventory.annotation.LoginUser
import site.enventory.annotation.WrapResponse
import site.enventory.api.user.dto.response.UserResponseDto
import site.enventory.constant.Uri
import site.enventory.entity.UserEntity

@RestController
@RequestMapping(Uri.V1 + Uri.USERS)
@WrapResponse
class UserController(
    private val userService: UserService
) {

    @Operation(
        summary = "유저 정보 조회",
        description = """
            Authorization Bearer Token을 해석하여 요청을 보낸 유저의 정보를 조회합니다.
        """
    )
    @GetMapping(Uri.INFO)
    fun getUserInfo(@LoginUser loginUser: UserEntity): Mono<UserResponseDto> {
        return Mono.just(UserResponseDto.of(loginUser))
    }
}