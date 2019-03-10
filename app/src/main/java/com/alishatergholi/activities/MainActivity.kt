package com.alishatergholi.activities

import android.os.Bundle
import com.alishatergholi.R
import com.alishatergholi.databinding.ActivityMainBinding
import com.alishatergholi.fragments.ProductFragment


class MainActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var binding = setContentView<ActivityMainBinding>(this,R.layout.activity_main)
        setFragment(binding.container.id,ProductFragment.newInstance())
    }



}
