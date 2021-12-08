package tech.minthura.mindvalley.domain.models

data class ErrorResponse(
    val description: String,
    val statusCode: Int
)