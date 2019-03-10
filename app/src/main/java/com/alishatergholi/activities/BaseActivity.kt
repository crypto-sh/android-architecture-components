package com.alishatergholi.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.databinding.ViewDataBinding



abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    protected fun <T : ViewDataBinding> setContentView(activity: AppCompatActivity, layoutId: Int): T {
        return DataBindingUtil.setContentView(activity, layoutId)
    }

    internal fun setFragment(resID : Int, fragment : Fragment){
        supportFragmentManager.beginTransaction().replace(resID,fragment).commit()
    }
}