package com.example.ecommerceshop.adapter

import android.content.Context
import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.ViewHolderColorBinding

class ColorAdapter(private val items:MutableList<String>):
    RecyclerView.Adapter<ColorAdapter.ViewHolder>() {
        private var selectedPoistion=-1
    private var lastSelectedPoistion=-1
    private lateinit var context: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorAdapter.ViewHolder {
        context=parent.context
        val binding=ViewHolderColorBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ColorAdapter.ViewHolder, position: Int) {
        val item=items[position]

        Glide.with(holder.itemView.context).load(item)
            .into(holder.binding.pic)
        holder.binding.root.setOnClickListener{
            lastSelectedPoistion=selectedPoistion
            selectedPoistion=position
            notifyItemChanged(lastSelectedPoistion)
            notifyItemChanged(selectedPoistion)
        }
        // Giả sử holder.binding.Tittle là một TextView
        if(selectedPoistion==position)
        {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg_selected)
        }
        else {
            holder.binding.colorLayout.setBackgroundResource(R.drawable.grey_bg)
        }
    }

    override fun getItemCount(): Int=items.size

    inner  class ViewHolder (val binding:ViewHolderColorBinding):
        RecyclerView.ViewHolder(binding.root){

    }
}