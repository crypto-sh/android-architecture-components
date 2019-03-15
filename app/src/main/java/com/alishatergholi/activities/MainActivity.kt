package com.alishatergholi.activities

import android.os.Bundle
import com.alishatergholi.R
import com.alishatergholi.databinding.ActivityMainBinding
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.fragments.ProductFragment
import com.alishatergholi.fragments.ProductListFragment
import com.alishatergholi.helper.ProductCallBack


class MainActivity : BaseActivity(), ProductCallBack {

    var binding: ActivityMainBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        setFragment(binding!!.container.id, ProductListFragment.newInstance())
    }

    override fun onClick(product: ProductEntity) {
        setFragment(binding!!.container.id, ProductFragment.newInstance(product.id))
    }






}
