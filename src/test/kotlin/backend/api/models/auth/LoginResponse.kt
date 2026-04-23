package backend.api.models.auth

data class LoginResponse(
    val id: Int,
    val accessToken: String,
    val refreshToken: String,
    val createdAt: Long,
    val expireInMs: Long
)