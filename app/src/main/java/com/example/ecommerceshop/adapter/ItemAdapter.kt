package com.example.ecommerceshop.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceshop.activity.DetailActivity
import com.example.ecommerceshop.activity.IntroActivity
import com.example.ecommerceshop.activity.MainActivity
import com.example.ecommerceshop.databinding.ViewHolderRecommendBinding
import com.example.ecommerceshop.model.ItemsModel

data class ItemAdapter(val items:MutableList<ItemsModel>): RecyclerView.Adapter<ItemAdapter.ViewHolder>() {
    private var context:Context?=null

 inner   class ViewHolder(val binding: ViewHolderRecommendBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
context=parent.context
        val binding=ViewHolderRecommendBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int =items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.tittleShoeTxt.text=items[position].title
        holder.binding.priceText.text="$"+items[position].price.toString()
        holder.binding.ratingtxt.text=items[position].rating.toString()

        val requestOption=RequestOptions().transform(CenterCrop())
        Glide.with(holder.itemView.context).load(items[position].picUrl[0])
            .apply  (requestOption)
            .into(holder.binding.pic)
        holder.itemView.setOnClickListener{
            val intent=Intent(holder.itemView.context,DetailActivity::class.java)
            Log.d("ItemAdapter", "Passing object: ${items[position]}")
            intent.putExtra("object",items[position])
            holder.itemView.context.startActivity(intent)
        }

    }
}