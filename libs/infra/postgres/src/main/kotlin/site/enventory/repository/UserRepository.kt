package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono
import site.enventory.entity.UserEntity
import java.util.UUID

interface UserRepository: R2dbcRepository<UserEntity, UUID> {

    fun findByEmail(email: String): Mono<UserEntity>
}