package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.enventory.entity.EnvStoreEntity
import site.enventory.repository.EnvStoreRepository

@Component
class EnvStoreAdapter(
    private val envStoreRepository: EnvStoreRepository
) {

    fun save(envStoreEntity: EnvStoreEntity): Mono<EnvStoreEntity> {
        return envStoreRepository.save(envStoreEntity)
    }
}