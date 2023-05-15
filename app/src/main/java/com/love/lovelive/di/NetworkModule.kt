package com.love.lovelive.di


import com.love.lovelive.retrofit.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {


    @Singleton
    @Provides
    fun providersLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor { message ->
            Timber.e(
               "Log: $message"
            )
        }
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        return interceptor
    }

    @Singleton
    @Provides
    fun providedAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }
//
    @Singleton
    @Provides
    fun providesOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        authenticationInterceptor: AuthenticationInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authenticationInterceptor)
            .readTimeout(240, TimeUnit.SECONDS)
            .connectTimeout(240, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(EndPoints.BaseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)

    }


    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): ApiService {
        return retrofit
            .build()
            .create(ApiService::class.java)
    }

    @Singleton
    @Provides
    fun provideRetrofitService(
        blogRetrofit: ApiService
    ): AppApiServices {
        return AppApiServiceImpl(blogRetrofit)
    }
}