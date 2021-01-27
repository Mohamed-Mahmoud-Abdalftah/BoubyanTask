package com.test.boubyan.ui.popular

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.test.boubyan.R
import com.test.boubyan.data.model.ApiPopular
import com.test.boubyan.data.model.ResultData
import kotlinx.android.synthetic.main.item_layout.view.*


class MostPopularAdapter(var clickListener: ClickListener, private val users: ArrayList<ResultData>)  : RecyclerView.Adapter<MostPopularAdapter.DataViewHolder>() {

    class DataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: ResultData,clickListener: ClickListener) {
user?.let {
    itemView.textViewUserName.text = user.title
    itemView.textViewUserEmail.text = user.byline
    Log.d("TAG", "bind:user.iduser.id "+user.id)
    if (user.media?.size!=0){
            Glide.with(itemView.imageViewAvatar.context)
        .load(user.media?.get(0)?.mediaMetadata?.get(0)?.url)
        .into(itemView.imageViewAvatar)
    }


    itemView.container.setOnClickListener {
        clickListener?.onItemClick(position, it)
    }
}

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        DataViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false))

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) =
        holder.bind(users[position],clickListener)

    fun addData(list: ApiPopular) {
        val resultData:List<ResultData>
        resultData= list.resultData!!
        users.addAll(resultData)

    }

}