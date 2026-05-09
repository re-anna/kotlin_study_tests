package backend.api.models.auth

data class LoginRequest(
    val email: String,
    val password: String
)

val defaultAdmin = LoginRequest(
    email = "admin@autotest.com",
    password = "admin"
)