package com.test.boubyan.data.api


import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

     override fun getMostPopular(period:Int,apiKey:String) = flow { emit(apiService.getUsers(period,apiKey)) }

}