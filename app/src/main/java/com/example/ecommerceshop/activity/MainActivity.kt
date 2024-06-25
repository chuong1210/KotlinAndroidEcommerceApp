package com.example.ecommerceshop.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import com.example.ecommerceshop.adapter.BrandAdapter
import com.example.ecommerceshop.adapter.ItemAdapter
import com.example.ecommerceshop.adapter.SliderAdapter
import com.example.ecommerceshop.databinding.ActivityMainBinding
import com.example.ecommerceshop.model.SliderModel
import com.example.ecommerceshop.view.MainViewModel


class MainActivity : BaseActivity() {
    private val viewModel=MainViewModel()
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initBrand()
        initInformItem()
        initBottomMenu()
    }

    private fun initBanner()
    {
        binding.progressBarBanner.visibility=View.VISIBLE
        viewModel.banner.observe(this,Observer
        {
            items->
            banners(items)
            binding.progressBarBanner.visibility=View.GONE
        })
        viewModel.loadBanners()
    }

    private fun banners(images:List<SliderModel>)
    {
        binding.viewPagerSlider.adapter= SliderAdapter(images,binding.viewPagerSlider)
        binding.viewPagerSlider.clipToPadding=false
        binding.viewPagerSlider.clipChildren=false
        binding.viewPagerSlider.offscreenPageLimit=3
        binding.viewPagerSlider.getChildAt(0).overScrollMode=RecyclerView.OVER_SCROLL_NEVER

        val compositePageTransformer=CompositePageTransformer().apply {
            addTransformer(MarginPageTransformer(40))
        }
        binding.viewPagerSlider.setPageTransformer(compositePageTransformer)
        if(images.size>1)
        {
            binding.dotsIndicator.visibility=View.VISIBLE
            binding.dotsIndicator.attachTo(binding.viewPagerSlider)

        }
    }

    private fun initBrand()
    {
        binding.progressBarBrand.visibility=View.VISIBLE
        viewModel.brand.observe(this,
            {
                binding.viewBrand.layoutManager=LinearLayoutManager(this@MainActivity,
                    LinearLayoutManager.HORIZONTAL,false)
                binding.viewBrand.adapter=BrandAdapter(it)
                binding.progressBarBrand.visibility=View.GONE
            })

        viewModel.loadBrands()
    }
    private fun initInformItem()
    {
       // binding.progressBarPopular.visibility=View.GONE
        
        binding.progressBarPopular.visibility=View.VISIBLE
        viewModel.inform.observe(this,
            {
                binding.viewPopular.layoutManager=GridLayoutManager(this@MainActivity,2)
                binding.viewPopular.adapter=ItemAdapter(it)
                binding.progressBarPopular.visibility=View.GONE
            })

        viewModel.loadInform()
    }

    private fun initBottomMenu()
    {
        // binding.progressBarPopular.visibility=View.GONE

        binding.cartMainBtn.setOnClickListener{
            startActivity(Intent(this@MainActivity,CartActivity::class.java))
        }
    }

    fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}