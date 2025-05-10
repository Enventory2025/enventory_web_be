package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.enventory.entity.EnvironmentEntity
import site.enventory.repository.EnvironmentRepository
import java.util.UUID

@Component
class EnvironmentAdapter(
    private val environmentRepository: EnvironmentRepository
) {

    fun save(environmentEntity: EnvironmentEntity): Mono<EnvironmentEntity> {
        return environmentRepository.save(environmentEntity)
    }

    fun createMany(envStoreId: UUID, environments: List<String>): Flux<EnvironmentEntity> {
        val environmentEntities = environments.map { EnvironmentEntity(envStoreId = envStoreId, name = it) }
        return environmentRepository.saveAll(environmentEntities)
    }
}