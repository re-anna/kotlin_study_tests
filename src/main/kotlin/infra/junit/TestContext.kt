package infra.junit

import backend.api.models.users.createUser.CreateUserResponse

object TestContext {
    var user: CreateUserResponse? = null
    var  token:  String? = null
}