package com.example.quizz.di.network

import com.example.quizz.BuildConfig
import com.example.quizz.data.networking.api.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val authInterceptor = Interceptor { chain ->
        val original = chain.request()

        val requestBuilder = original.newBuilder()
            .header("Authorization", HEADER)
            .method(original.method, original.body)

        val request = requestBuilder.build()
        chain.proceed(request)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BuildConfig.WEBSERVICE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    private const val HEADER = "Basic ZGVmYXVsdC11c2VyOnNhZGZvaWozNGw1a2phc2RmbGsgajAzOTRqd2Fsc2lramRmO2xha3NkamYgLmFzZGZranpraiM="
}
