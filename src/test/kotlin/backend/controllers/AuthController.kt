package backend.controllers

import backend.api.endpoints.Endpoints
import backend.api.models.auth.LoginRequest
import io.qameta.allure.Step

class AuthController: Endpoints(){

    @Step("Login with email: {email} and password: {password}")
    fun login(email: String, password: String) = auth.postLogin(body = LoginRequest(email = email, password = password))
        .execute()
}