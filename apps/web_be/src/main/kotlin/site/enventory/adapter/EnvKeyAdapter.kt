package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.enventory.entity.EnvKeyEntity
import site.enventory.repository.EnvKeyRepository

@Component
class EnvKeyAdapter(
    private val envKeyRepository: EnvKeyRepository
) {

    fun save(envKeyEntity: EnvKeyEntity): Mono<EnvKeyEntity> {
        return envKeyRepository.save(envKeyEntity)
    }
}