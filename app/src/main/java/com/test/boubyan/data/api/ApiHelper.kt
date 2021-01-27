package com.test.boubyan.data.api

import com.test.boubyan.data.model.ApiPopular
import kotlinx.coroutines.flow.Flow

interface ApiHelper {

    fun getMostPopular(period:Int,apiKey:String): Flow<ApiPopular>

}