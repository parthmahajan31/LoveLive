package com.love.lovelive.di



import com.love.lovelive.interactors.UserUseCase
import com.love.lovelive.retrofit.AppApiServices
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object InteractorsModule {

    @Singleton
    @Provides
    fun provideGetUserCase(
        networkDataSource: AppApiServices
    ): UserUseCase {
        return UserUseCase(networkDataSource)
    }
}