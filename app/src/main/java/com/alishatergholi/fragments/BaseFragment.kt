package com.alishatergholi.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {

    protected fun <T : ViewDataBinding> setContentView(inflater: LayoutInflater,container: ViewGroup, layoutId: Int): T {
        return DataBindingUtil.inflate(inflater, layoutId, container, false)
    }

}