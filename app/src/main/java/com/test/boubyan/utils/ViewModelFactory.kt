package com.test.boubyan.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.test.boubyan.data.api.ApiHelper
import com.test.boubyan.ui.popular.MostPopularViewModel

class ViewModelFactory(
    private val apiHelper: ApiHelper,
    private val period:Int,
    private val apiKey:String
    ) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MostPopularViewModel::class.java)) {
            return MostPopularViewModel(apiHelper,period,apiKey) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }

}