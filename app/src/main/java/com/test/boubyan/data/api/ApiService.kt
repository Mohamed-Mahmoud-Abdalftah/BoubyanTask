package com.test.boubyan.data.api

import com.test.boubyan.data.model.ApiPopular
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {


    @Headers("Accept: application/json", "Content-Type:  application/json")
    @GET("mostpopular/v2/viewed/{period}.json")
    suspend fun getUsers(@Path("period") period: Int, @Query("api-key") apiKey: String): ApiPopular


}