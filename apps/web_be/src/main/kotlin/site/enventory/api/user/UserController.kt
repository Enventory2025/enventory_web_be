package site.enventory.api.user

import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import site.enventory.api.user.dto.request.SaveUserRequestDto
import site.enventory.entity.UserEntity

@RestController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {

    @PostMapping
    fun saveUser(@RequestBody @Valid requestDto: SaveUserRequestDto): Mono<UserEntity> {
        return userService.save(requestDto)
    }
}