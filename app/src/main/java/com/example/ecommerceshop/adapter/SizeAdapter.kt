package com.example.ecommerceshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.ecommerceshop.R
import com.example.ecommerceshop.databinding.ViewHolderSizeBinding

class SizeAdapter(private val items:MutableList<String>):
    RecyclerView.Adapter<SizeAdapter.ViewHolder>() {
        private var selectedPoistion=-1
    private var lastSelectedPoistion=-1
    private lateinit var context: Context



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SizeAdapter.ViewHolder {
        context=parent.context
        val binding=ViewHolderSizeBinding.inflate(LayoutInflater.from(context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SizeAdapter.ViewHolder, position: Int) {
        val item=items[position]
        holder.binding.sizeText.text=item.toString()

        holder.binding.root.setOnClickListener{
            lastSelectedPoistion=selectedPoistion
            selectedPoistion=position
            notifyItemChanged(lastSelectedPoistion)
            notifyItemChanged(selectedPoistion)
        }
        // Giả sử holder.binding.Tittle là một TextView
        if(selectedPoistion==position)
        {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg_selected)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.purple_700))
        }
        else {
            holder.binding.sizeLayout.setBackgroundResource(R.drawable.grey_bg)
            holder.binding.sizeText.setTextColor(context.resources.getColor(R.color.black))

        }
    }

    override fun getItemCount(): Int=items.size

    inner  class ViewHolder (val binding:ViewHolderSizeBinding):
        RecyclerView.ViewHolder(binding.root){

    }
}