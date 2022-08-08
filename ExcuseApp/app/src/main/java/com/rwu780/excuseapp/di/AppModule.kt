package com.rwu780.excuseapp.di

import com.rwu780.excuseapp.data.ExcuseRepositoryImpl
import com.rwu780.excuseapp.data.remote.ExcuseApi
import com.rwu780.excuseapp.domain.repository.ExcuseRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    @Provides
    @Singleton
    fun provideDispatcher() : CoroutineDispatcher = Dispatchers.Default

    @Provides
    @Singleton
    fun provideExcuseApi(moshi: Moshi) : ExcuseApi {
        return Retrofit.Builder().baseUrl(ExcuseApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExcuseApi::class.java)
    }

    @Provides
    @Singleton
    fun provideExcuseRepository(api: ExcuseApi) : ExcuseRepository {
        return  ExcuseRepositoryImpl(api)
    }

}