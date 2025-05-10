package site.enventory.api.envstore

import org.springframework.stereotype.Service
import site.enventory.adapter.EnvStoreAdapter

@Service
class EnvStoreService(
    private val envStoreAdapter: EnvStoreAdapter
) {
}