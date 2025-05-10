package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono
import site.enventory.entity.UserEntity

interface UserRepository: R2dbcRepository<UserEntity, Long> {

    fun findByEmail(email: String): Mono<UserEntity>
}