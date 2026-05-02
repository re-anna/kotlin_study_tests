package backend.api.models.users.createUser

import kotlin.random.Random

data class CreateUserRequest(
    val username: String,
    val password: String,
    val email: String?
)

fun defaultUser() = CreateUserRequest(
        username = "default",
        password = "user",
        email = "default@autotest.com"
    )

fun randomUser() = CreateUserRequest(
    username = "random",
    password = "user",
    email = "random-${Random.nextInt(10000)}@autotest.com"
)