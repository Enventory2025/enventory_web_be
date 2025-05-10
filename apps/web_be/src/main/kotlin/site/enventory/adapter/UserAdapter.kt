package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.enventory.entity.UserEntity
import site.enventory.repository.UserRepository

@Component
class UserAdapter(
    private val userRepository: UserRepository
) {

    fun save(userEntity: UserEntity): Mono<UserEntity> {
        return userRepository.save(userEntity)
    }
}