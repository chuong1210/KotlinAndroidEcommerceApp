package com.example.ecommerceshop.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.request.RequestOptions
import com.example.ecommerceshop.databinding.ViewHolderCartBinding
import com.example.ecommerceshop.helper.ManagmentCart
import com.example.ecommerceshop.model.ItemsModel
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartAdapter(private val listItemSelected:ArrayList<ItemsModel>,context:Context,
                  var changeNumberItemsListener: ChangeNumberItemsListener?=null):  RecyclerView
.Adapter<CartAdapter.ViewHolder>() {
    private val managmentCart=ManagmentCart(context)//holder.itemview.context
    class ViewHolder(val binding: ViewHolderCartBinding) : RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartAdapter.ViewHolder
    {
val binding=ViewHolderCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CartAdapter.ViewHolder, position: Int) {
        val item=listItemSelected[position]
        holder.binding.titleText.text=item.title
        holder.binding.feeEachItemText.text="${item.price}VNĐ"
        holder.binding.totalEachItemText.text="${Math.round(item.price*item.numberInCart)}VNĐ"
        holder.binding.numberItemText.text=item.numberInCart.toString()
        Glide.with(holder.itemView.context)
            .load(item.picUrl[0])
            .apply (RequestOptions().transform(CenterCrop()))
            .into(holder.binding.pic)
        holder.binding.plusCartBtn.setOnClickListener{
            managmentCart.plusItem(listItemSelected,position,object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }

        holder.binding.minusCartBtn.setOnClickListener{
            managmentCart.minusItem(listItemSelected,position,object : ChangeNumberItemsListener{
                override fun onChanged() {
                    notifyDataSetChanged()
                    changeNumberItemsListener?.onChanged()
                }

            })
        }


    }

    override fun getItemCount(): Int=listItemSelected.size
}