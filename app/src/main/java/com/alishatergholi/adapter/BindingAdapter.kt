package com.alishatergholi.adapter

import android.view.View
import androidx.databinding.BindingAdapter

class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter("visibleGone")
        fun View.setVisibility(show: Boolean) {
            this.visibility = if (show) View.VISIBLE else View.GONE
        }
    }
}