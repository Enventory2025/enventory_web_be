package site.enventory.api.envstore

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import site.enventory.adapter.EnvStoreAdapter
import site.enventory.adapter.EnvironmentAdapter
import site.enventory.api.envstore.dto.request.CreateEnvStoreRequestDto
import site.enventory.api.envstore.dto.response.EnvStoreResponseDto
import site.enventory.entity.EnvStoreEntity
import site.enventory.entity.UserEntity

@Service
class EnvStoreService(
    private val envStoreAdapter: EnvStoreAdapter,
    private val environmentAdapter: EnvironmentAdapter
) {

    fun getAll(loginUser: UserEntity): Flux<EnvStoreResponseDto> {
        return envStoreAdapter.findAllByUserId(loginUser.id!!)
            .map { EnvStoreResponseDto.of(it) }
    }

    @Transactional
    fun create(
        loginUser: UserEntity,
        requestDto: CreateEnvStoreRequestDto
    ): Mono<EnvStoreResponseDto> {
        return envStoreAdapter.save(
            EnvStoreEntity(userId = loginUser.id!!, name = requestDto.name)
        ).flatMap { envStore ->
            environmentAdapter.createMany(
                envStoreId = envStore.id!!,
                environments = requestDto.environments
            ).then(Mono.just(EnvStoreResponseDto.of(envStore)))
        }
    }
}