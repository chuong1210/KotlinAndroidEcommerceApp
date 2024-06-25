package com.example.ecommerceshop.activity

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ecommerceshop.R
import com.example.ecommerceshop.adapter.CartAdapter
import com.example.ecommerceshop.databinding.ActivityCartBinding
import com.example.ecommerceshop.databinding.ViewHolderCartBinding
import com.example.ecommerceshop.helper.ManagmentCart
import com.example.project1762.Helper.ChangeNumberItemsListener

class CartActivity : BaseActivity() {
    private lateinit var  binding:ActivityCartBinding
    private lateinit var managmentCart: ManagmentCart
    private var tax:Double=0.0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityCartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        managmentCart=ManagmentCart(this);

        setVariable()
        initCartList()
        caculateCart()
    }

    private fun initCartList() {
        binding.viewCart.layoutManager=LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        binding.viewCart.adapter=CartAdapter(managmentCart.getListCart(),this,object :ChangeNumberItemsListener{
            override fun onChanged() {
                caculateCart()
            }

        })
        with(binding)
        {
            emptyTxt.visibility=if(managmentCart.getListCart().isEmpty()) View.VISIBLE else View.GONE
            scrollViewCart.visibility=if(managmentCart.getListCart().isEmpty()) View.GONE else View.VISIBLE

        }

    }

    private fun setVariable()
    {
        binding.backBtn.setOnClickListener{finish()}
    }
    private fun caculateCart()
    {
        var precentTax=0.02
        var delivery=10
        tax=Math.round((managmentCart.getTotalFee()*precentTax)*100)/100.0
        val totalFee=Math.round((managmentCart.getTotalFee()+tax+delivery)*100)/100.0
        val itemTotalFee=Math.round(managmentCart.getTotalFee()*100)/100.0

        with(binding)
        {
            subTxt.text="VNĐ${itemTotalFee}"
            totalTxtFee.text="VNĐ${totalFee}"
            taxTxt.text="VNĐ${tax}"
            deliFeeTxt.text="VNĐ${delivery}"


        }



    }


}