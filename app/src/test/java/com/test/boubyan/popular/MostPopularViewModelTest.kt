package com.test.boubyan.popular

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.mindorks.kotlinFlow.utils.Resource
import com.mindorks.kotlinFlow.utils.TestCoroutineRule
import com.test.boubyan.data.api.ApiHelper
import com.test.boubyan.data.model.ApiPopular
import com.test.boubyan.data.model.ResultData
import com.test.boubyan.ui.popular.MostPopularViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.doReturn
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MostPopularViewModelTest {
    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()
    @get:Rule
    val testCoroutineRule = TestCoroutineRule()
    @Mock
    private lateinit var apiHelper: ApiHelper
    @Mock
    private lateinit var apiUsersObserver: Observer<Resource<ApiPopular>>
    val period:Int =7
    val apiKey:String ="lDE7dnhTUCMgVmJGwBr5r47A6RKopu0z"
    @Before
    fun setUp() {
        // do something if required
    }
    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
           doReturn(flowOf(ApiPopular()))
                .`when`(apiHelper)
                .getMostPopular(period,apiKey)
            val viewModel = MostPopularViewModel(apiHelper,period,apiKey)
            viewModel.getUsers().observeForever(apiUsersObserver)
            verify(apiHelper).getMostPopular(period,apiKey)
            verify(apiUsersObserver).onChanged(Resource.success(ApiPopular()))
            viewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val errorMessage = "Error Message For You"
           doReturn(flow<List<ApiPopular>> {
                throw IllegalStateException(errorMessage)
            })
                .`when`(apiHelper)
                .getMostPopular(period,apiKey)
            val viewModel = MostPopularViewModel(apiHelper,period,apiKey )
            viewModel.getUsers().observeForever(apiUsersObserver)
            verify(apiHelper).getMostPopular(period,apiKey)
            verify(apiUsersObserver).onChanged(
                Resource.error(
                    IllegalStateException(errorMessage).toString(),
                    null
                )
            )
            viewModel.getUsers().removeObserver(apiUsersObserver)
        }
    }
    @After
    fun tearDown() {
        // do something if required
    }
}