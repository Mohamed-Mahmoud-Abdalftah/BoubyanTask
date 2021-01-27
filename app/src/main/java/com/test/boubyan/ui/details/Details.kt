package com.test.boubyan.ui.details

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.test.boubyan.R
import com.test.boubyan.data.model.ResultData
import kotlinx.android.synthetic.main.activity_details.*
import kotlinx.android.synthetic.main.content_scrolling.*
import kotlinx.android.synthetic.main.item_layout.view.*

class Details : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setupUI()



    }

    private fun setupUI() {
        val image:String = intent.getStringExtra("image").toString()
        val title:String = intent.getStringExtra("title").toString()
        val author:String = intent.getStringExtra("author").toString()
        val content:String = intent.getStringExtra("content").toString()
        val date:String = intent.getStringExtra("date").toString()

        Glide.with(this)
            .load(image)
            .into(imageViewAvatar)
        titleTextView.text=title
        authorTextView.text=author
        contentTextView.text=content
        dateTextView.text=date
    }


}