package site.enventory.repository

import org.springframework.data.r2dbc.repository.R2dbcRepository
import reactor.core.publisher.Mono
import site.enventory.entity.EnvValueEntity
import java.util.UUID

interface EnvValueRepository: R2dbcRepository<EnvValueEntity, UUID> {

    fun findByEnvKeyIdAndEnvironmentId(envKeyId: UUID, environmentId: UUID): Mono<EnvValueEntity>
}