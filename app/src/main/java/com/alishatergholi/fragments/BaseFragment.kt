package com.alishatergholi.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * extends all of your fragment from this one.
 * you can add public method which you need to use in subclass
 * in this class.
 */

abstract class BaseFragment : Fragment() {

    protected fun <T : ViewDataBinding> setContentView(inflater: LayoutInflater,container: ViewGroup, layoutId: Int): T {
        return DataBindingUtil.inflate(inflater, layoutId, container, false)
    }

}