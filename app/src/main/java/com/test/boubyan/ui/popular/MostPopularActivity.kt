package com.test.boubyan.ui.popular

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.mindorks.kotlinFlow.utils.Status
import com.test.boubyan.R
import com.test.boubyan.data.api.ApiHelperImpl
import com.test.boubyan.data.api.RetrofitBuilder
import com.test.boubyan.data.model.ApiPopular
import com.test.boubyan.data.model.ResultData
import com.test.boubyan.ui.details.Details
import com.test.boubyan.utils.ViewModelFactory
import kotlinx.android.synthetic.main.most_popular_activity.*
import java.io.Serializable

class MostPopularActivity : AppCompatActivity(), ClickListener {
    private lateinit var viewModel: MostPopularViewModel
    private lateinit var adapter: MostPopularAdapter
    private lateinit var resultData: ArrayList<ResultData>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.most_popular_activity)
        setupUI()
        setupViewModel()
        setupObserver()
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter =
            MostPopularAdapter(
                this, arrayListOf()
            )
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter

    }

    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService),7,"lDE7dnhTUCMgVmJGwBr5r47A6RKopu0z")
        ).get(MostPopularViewModel::class.java)
    }

    private fun setupObserver() {
        viewModel.getUsers().observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { users -> renderList(users)
                       }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun renderList(list: ApiPopular) {
        adapter.addData(list)
        adapter.notifyDataSetChanged()
        resultData= list.resultData as ArrayList<ResultData>

    }

    override fun onItemClick(position: Int, v: View?) {
        val intent = Intent(this, Details::class.java)
        if ( resultData?.get(position).media?.size!=0)
            intent.putExtra("image",  resultData?.get(position).media?.get(0)?.mediaMetadata?.get(2)?.url)
            intent.putExtra("title", resultData.get(position).title)
            intent.putExtra("author",  resultData.get(position).byline)
            intent.putExtra("content", resultData.get(position).abstract)
            intent.putExtra("date",resultData.get(position).publishedDate)
        startActivity(intent)

    }


}