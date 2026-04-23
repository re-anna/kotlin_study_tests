package backend.api.models

data class ErrorResponse(
    var code: Int,
    var reason: String
)
