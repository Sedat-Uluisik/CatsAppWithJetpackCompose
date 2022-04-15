package com.sedat.catsappwithjetpackcompose.hilt

import com.sedat.catsappwithjetpackcompose.api.CatApi
import com.sedat.catsappwithjetpackcompose.Util.Util.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object Hilt {
    @Singleton
    @Provides
    fun injectRetrofit(): CatApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(CatApi::class.java)
    }
}