package io.n26btc.data.api

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import io.n26btc.data.BuildConfig
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

class RetrofitFactory {

  @ExperimentalSerializationApi fun build(json: Json): Retrofit {
    return Retrofit.Builder()
      .addConverterFactory(json.asConverterFactory("application/json".toMediaType()))
      .client(getClient())
      .baseUrl(BuildConfig.BASE_API)
      .build()
  }

  private fun getClient(): OkHttpClient {
    val builder = OkHttpClient.Builder()

    addTimeouts(builder)

    addBearerInterceptor(builder)

    val interceptor = HttpLoggingInterceptor()
    interceptor.level = HttpLoggingInterceptor.Level.BODY
    builder.addInterceptor(interceptor)

    return builder.build()
  }

  private fun addBearerInterceptor(builder: OkHttpClient.Builder) {
    builder.addInterceptor { chain ->

      val newRequest = chain.request()
        .newBuilder()
        .build()
      chain.proceed(newRequest)
    }
  }

  private fun addTimeouts(builder: OkHttpClient.Builder, timeout: Int = 30) {
    builder.connectTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .writeTimeout(timeout.toLong(), TimeUnit.SECONDS)
      .readTimeout(timeout.toLong(), TimeUnit.SECONDS)
  }
}