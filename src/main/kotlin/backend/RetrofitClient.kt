package org.example.backend

import io.qameta.allure.okhttp3.AllureOkHttp3
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.example.general.Config
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.time.Duration

object RetrofitClient {

    private val timeout = Duration.ofSeconds(10)
    private val client = OkHttpClient.Builder()
        .retryOnConnectionFailure(true)
        .callTimeout(timeout)
        .readTimeout(timeout)
    //.writetimeout
    //добавляем интерсептор
        .addInterceptor { chain: Interceptor.Chain ->
            val builder = chain.request().newBuilder()
            chain.proceed(builder.build())
        }
        .addInterceptor(AllureOkHttp3())
        .build()

    //инициализируем объявляем клиент.
    fun <T> createService(service: Class<T>): T =
        Retrofit.Builder()
            .baseUrl(Config.get.backendUrl)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(service)
}