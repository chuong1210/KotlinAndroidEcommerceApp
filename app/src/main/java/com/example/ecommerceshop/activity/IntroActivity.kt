package com.example.ecommerceshop.activity

import android.content.Intent
import android.os.Bundle
import com.example.ecommerceshop.databinding.ActivityIntroBinding

class IntroActivity : BaseActivity() {
    private lateinit var binding:ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityIntroBinding.inflate(layoutInflater);
        //setContentView(R.layout.activity_intro)
        setContentView(binding.root)
        binding.StartBtn.setOnClickListener {
            // Create an Intent to launch MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

            // Finish this activity to prevent going back
            finish()
        }

    }
}