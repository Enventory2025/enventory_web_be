package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import site.enventory.entity.EnvStoreEntity
import java.util.UUID

interface EnvStoreRepository: R2dbcRepository<EnvStoreEntity, UUID> {

    fun findAllByUserId(userId: UUID): Flux<EnvStoreEntity>
}