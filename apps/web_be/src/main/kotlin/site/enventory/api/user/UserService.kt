package site.enventory.api.user

import org.springframework.stereotype.Service
import site.enventory.adapter.UserAdapter

@Service
class UserService(
    private val userAdapter: UserAdapter
) {

}