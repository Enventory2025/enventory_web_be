package site.enventory.api.envstore

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import site.enventory.adapter.EnvStoreAdapter
import site.enventory.api.envstore.dto.response.EnvStoreResponseDto
import site.enventory.entity.UserEntity

@Service
class EnvStoreService(
    private val envStoreAdapter: EnvStoreAdapter
) {
    fun getAll(loginUser: UserEntity): Flux<EnvStoreResponseDto> {
        return envStoreAdapter.findAllByUserId(loginUser.id!!)
            .map { EnvStoreResponseDto.of(it) }
    }
}