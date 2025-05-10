package site.enventory.response

data class EnventoryResponse<T>(
    val message: String = "success",
    val data: T? = null,
) {
    override fun toString(): String {
        return """
            {
                "message": "$message"${data?.let { """, "data": $it""" } ?: ""}
            }
        """.trimIndent()
    }
}