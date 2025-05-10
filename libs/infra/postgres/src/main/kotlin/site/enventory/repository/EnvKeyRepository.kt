package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import site.enventory.entity.EnvKeyEntity
import java.util.UUID

interface EnvKeyRepository: R2dbcRepository<EnvKeyEntity, UUID> {

    fun findAllByEnvStoreId(envStoreId: UUID): Flux<EnvKeyEntity>
}