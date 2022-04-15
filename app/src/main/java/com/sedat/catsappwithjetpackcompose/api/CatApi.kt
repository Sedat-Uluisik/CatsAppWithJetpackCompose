package com.sedat.catsappwithjetpackcompose.api

import com.sedat.catsappwithjetpackcompose.model.CatItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CatApi {
    @GET("/v1/breeds")
    suspend fun getCats(
        @Query("page") page: Int = 1,
        @Query("attach_breed") attach_breed: Int = 0,
        @Query("limit") limit: Int = 17,
    ): Response<List<CatItem>>

    @GET("/v1/breeds/search")
    suspend fun search(
        @Query("q") query: String
    ): Response<List<CatItem>>
}