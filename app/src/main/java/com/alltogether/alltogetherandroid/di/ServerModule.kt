package com.alltogether.alltogetherandroid.di

import android.content.Context
import com.alltogether.alltogetherandroid.api.ServerService
import com.alltogether.alltogetherandroid.api.UserService
import com.alltogether.alltogetherandroid.repository.NaverOAuthRepository
import com.alltogether.alltogetherandroid.repository.NaverOAuthRepositoryImpl
import com.alltogether.alltogetherandroid.repository.ServerRepository
import com.alltogether.alltogetherandroid.repository.ServerRepositoryImpl
import com.google.gson.GsonBuilder
import com.nhn.android.naverlogin.OAuthLogin
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val serverModule = module {
    factory { provideOkHttpClient()}
    factory { provideServerApi(provideServerRetrofit(get())) }
    factory { provideUserApi(provideUserRetrofit(get())) }

    single<ServerRepository> { ServerRepositoryImpl(get()) }
    single<NaverOAuthRepository> { NaverOAuthRepositoryImpl(get()) }
    single { provideOAuthModule(get()) }
}


fun provideServerApi(retrofit: Retrofit): ServerService = retrofit.create(ServerService::class.java)
fun provideUserApi(retrofit: Retrofit):UserService = retrofit.create(UserService::class.java)

fun provideServerRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("http://18.191.56.95:8080").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()).build()
}

fun provideUserRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl("https://openapi.naver.com").client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(
            RxJava2CallAdapterFactory.create()).build()
}

fun provideOkHttpClient(): OkHttpClient {
    val httpClientBuilder = OkHttpClient().newBuilder()
    val logging = HttpLoggingInterceptor()
    logging.level = HttpLoggingInterceptor.Level.BODY
    httpClientBuilder.addInterceptor(logging)

    return httpClientBuilder.build()
}

private fun provideOAuthModule(context : Context) : OAuthLogin {
    val module : OAuthLogin = OAuthLogin.getInstance()
    module.init(context, "ySq6zKNHSpHSkTcFbbG7", "dOoHOy9lfK", "올투게더" )
    return module
}