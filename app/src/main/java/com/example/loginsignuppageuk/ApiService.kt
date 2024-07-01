package com.example.login_registration_app_kt

import com.example.loginsignuppageuk.LoginRequest
import com.example.loginsignuppageuk.SignupRequest
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("Login/")
    fun signIn(@Body request: LoginRequest): Call<LoginResponce>

    @POST("Register/")
    fun signUp(@Body request: SignupRequest): Call<SignupResponce>
}
