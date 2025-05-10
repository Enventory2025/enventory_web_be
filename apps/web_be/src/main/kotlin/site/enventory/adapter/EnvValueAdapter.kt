package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.enventory.entity.EnvValueEntity
import site.enventory.repository.EnvValueRepository

@Component
class EnvValueAdapter(
    private val envValueRepository: EnvValueRepository
) {

    fun save(envValueEntity: EnvValueEntity): Mono<EnvValueEntity> {
        return envValueRepository.save(envValueEntity)
    }
}