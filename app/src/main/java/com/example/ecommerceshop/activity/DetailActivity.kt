package com.example.ecommerceshop.activity

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Size
import android.view.LayoutInflater
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ecommerceshop.R
import com.example.ecommerceshop.adapter.ColorAdapter
import com.example.ecommerceshop.adapter.SizeAdapter
import com.example.ecommerceshop.adapter.SliderAdapter
import com.example.ecommerceshop.databinding.ActivityDetailBinding
import com.example.ecommerceshop.helper.ManagmentCart
import com.example.ecommerceshop.model.ItemsModel
import com.example.ecommerceshop.model.SliderModel
import java.util.ResourceBundle
import java.util.ResourceBundle.getBundle

class DetailActivity : BaseActivity() {
    private lateinit var binding:ActivityDetailBinding
    private lateinit var item:ItemsModel
    private var numberOrder=1
    lateinit var managmentCart: ManagmentCart
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding=ActivityDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)
        managmentCart=ManagmentCart(this)
        getBundle()
        banners()
        initList()

    }

    private fun initList() {
        val sizeList=ArrayList<String>()
        for (size in item.size)
        {
            sizeList.add(size.toString())
        }
        binding.listSize.adapter = SizeAdapter(sizeList)
        binding.listSize.layoutManager=
            LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL,
                false)

        val colorList=ArrayList<String>()
        for (imageUrl in item.picUrl)
        {
            colorList.add(imageUrl)
        }
        binding.colorList.adapter=ColorAdapter(colorList)
        binding.colorList.layoutManager=LinearLayoutManager(this
            ,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun banners() {
    val sliderItem = ArrayList<SliderModel>()
    for (imageUrl in item.picUrl) {
        sliderItem.add(SliderModel(imageUrl))
    }
    binding.slider.adapter = SliderAdapter(sliderItem, binding.slider)
    binding.slider.clipToPadding = true
    binding.slider.clipChildren = true
    binding.slider.offscreenPageLimit = 3
    //binding.slider.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER

    if (sliderItem.size>1)
    {
        binding.dotsIndicator.visibility= View.VISIBLE
        binding.dotsIndicator.attachTo(binding.slider)
    }
}

private fun getBundle() {
        item = intent.getParcelableExtra<ItemsModel>("object")!!
        binding.titleText.text=item.title
        binding.descriptionText.text=item.description
        binding.priceText.text=item.price.toString()+"VNĐ"
        binding.ratingText.text="${item.rating} Rating"
        binding.addtoCartBtn.setOnClickListener{
            item.numberInCart=numberOrder
            managmentCart.insertFood(item)

        }
        binding.backBtn.setOnClickListener{
            finish()
        }
        binding.cartBtn.setOnClickListener{
startActivity(Intent(this@DetailActivity,CartActivity::class.java))
        }
    }
}