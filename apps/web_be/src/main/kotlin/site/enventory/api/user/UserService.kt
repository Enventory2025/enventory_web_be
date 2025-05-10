package site.enventory.api.user

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import site.enventory.adapter.UserAdapter
import site.enventory.api.user.dto.request.SaveUserRequestDto
import site.enventory.entity.UserEntity

@Service
class UserService(
    private val userAdapter: UserAdapter
) {

    fun save(saveDto: SaveUserRequestDto): Mono<UserEntity> {
        val userEntity = UserEntity(
            email = saveDto.email,
            provider = saveDto.provider,
            providerId = saveDto.providerId,
        )
        return userAdapter.save(userEntity)
    }
}