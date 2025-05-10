package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Flux
import site.enventory.entity.EnvironmentEntity
import java.util.UUID

interface EnvironmentRepository: R2dbcRepository<EnvironmentEntity, UUID> {

    fun findAllByEnvStoreId(envStoreId: UUID): Flux<EnvironmentEntity>
}