package site.enventory.response

data class EnventoryResponse<T>(
    val message: String = "success",
    val data: T
)