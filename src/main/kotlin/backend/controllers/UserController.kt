package backend.controllers

import backend.api.endpoints.Endpoints
import backend.helpers.AuthHelper
import backend.helpers.GarbageCollector
import backend.api.models.users.createUser.CreateUserRequest
import backend.api.models.users.createUser.CreateUserResponse
import backend.api.models.users.updateUser.UpdateRequest
import backend.extension.ResponseExt.Companion.getAsObject
import io.qameta.allure.Step
import okhttp3.ResponseBody
import retrofit2.Response

class UserController: Endpoints(){

    private val authHelper = AuthHelper()

    @Step("Create new user")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse>{
        return users.createUser(body).execute()
            .also { GarbageCollector.user.add(it.getAsObject().id) }
    }

    @Step("Get user with {id}")
    fun getUserById(token: String? = authHelper.getAdminToken(), id: Int): Response<CreateUserResponse>{
        return users.getUserById(token,id).execute()
    }

    @Step("Get all users")
    fun getAllUsers(token: String? = authHelper.getAdminToken(), offset: Int = 0, limit: Int = 50):
            Response<List<CreateUserResponse>> {
        return users.getUsers(token,offset,limit).execute()
    }

    @Step("Update users with ID: {id}")
    fun updateUserById(token: String? = authHelper.getAdminToken(), id: Int, body: UpdateRequest): Response<CreateUserResponse>{
        return users.putUserById(token,id, body).execute()
    }

    @Step("Delete user with ID: {id}")
    fun deleteUserById(token: String? = authHelper.getAdminToken(), id: Int): Response<ResponseBody>{
        return users.deleteUserById(token,id).execute()
    }
}
