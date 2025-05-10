package site.enventory.adapter

import org.springframework.stereotype.Component
import reactor.core.publisher.Mono
import site.enventory.entity.EnvironmentEntity
import site.enventory.repository.EnvironmentRepository

@Component
class EnvironmentAdapter(
    private val environmentRepository: EnvironmentRepository
) {

    fun save(environmentEntity: EnvironmentEntity): Mono<EnvironmentEntity> {
        return environmentRepository.save(environmentEntity)
    }
}