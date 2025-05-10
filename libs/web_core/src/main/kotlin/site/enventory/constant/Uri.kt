package site.enventory.constant

object Uri {
    const val HEALTH = "/_health"

    const val V1 = "/v1"
    const val V3 = "/v3"
    const val API_DOCS = "/api-docs"
    const val WEBJARS = "/webjars"

    const val AUTH = "/auth"
    const val LOGIN = "/login"
    const val REFRESH = "/refresh"

    const val USERS = "/users"
    const val INFO = "/info"

    val GET_WHITELIST = listOf(
        HEALTH,
        API_DOCS,
        WEBJARS + "/**",
        V3 + API_DOCS + "/**",
    )

    val POST_WHITELIST = listOf(
        AUTH + LOGIN,
        AUTH + REFRESH,
    )
}