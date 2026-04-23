package org.example.backend.controllers.endpoints

import org.example.backend.controllers.endpoints.models.auth.LoginRequest
import org.example.backend.controllers.endpoints.models.auth.LoginResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthEndpoints {

    @POST("auth/login")
    fun postLogin(@Body body: LoginRequest) : Call<LoginResponse>
}