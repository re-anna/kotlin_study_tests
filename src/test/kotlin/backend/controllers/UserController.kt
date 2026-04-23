package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.helpers.AuthHelper
import backend.api.models.users.CreateUserRequest
import backend.api.models.users.CreateUserResponse
import io.qameta.allure.Step
import retrofit2.Response

class UserController: Endpoints(){

    private val authHelper = AuthHelper()

    @Step("Create new user")
    fun createUser(body: CreateUserRequest): Response<CreateUserResponse>{
        return users.createUser(body).execute()
    }

    }
