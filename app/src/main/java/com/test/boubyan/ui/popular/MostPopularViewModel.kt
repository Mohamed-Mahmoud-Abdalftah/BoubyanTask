package com.test.boubyan.ui.popular

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindorks.kotlinFlow.utils.Resource

import com.test.boubyan.data.api.ApiHelper
import com.test.boubyan.data.model.ApiPopular
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect

class MostPopularViewModel(
    private val apiHelper: ApiHelper,
    private val period:Int,
    private val apiKey:String
)  : ViewModel() {

    private val users = MutableLiveData<Resource<ApiPopular>>()

    init {
        fetchUsers(period,apiKey)
    }

    private fun fetchUsers(period:Int,apiKey:String) {
        viewModelScope.launch {
            users.postValue(Resource.loading(null))
            apiHelper.getMostPopular(period,apiKey)
                .catch { e ->
                    users.postValue(Resource.error(e.toString(), null))
                }
                .collect {
                    users.postValue(Resource.success(it))
                }
        }
    }

    fun getUsers(): LiveData<Resource<ApiPopular>> {
        return users
    }

}