package com.alishatergholi.adapter

import android.view.LayoutInflater

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.alishatergholi.databinding.ProductItemBinding
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.helper.ProductCallBack


class ProductAdapter(val callBack: ProductCallBack) : RecyclerView.Adapter<ProductAdapter.ProductHoldet>() {

    val products = ArrayList<ProductEntity>()

    fun setProducts(items : List<ProductEntity>){
        this.products.addAll(items)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHoldet {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ProductItemBinding.inflate(inflater, parent,false)
        return ProductHoldet(binding,callBack)
    }

    override fun getItemCount(): Int {
        return products.size
    }

    override fun onBindViewHolder(holder: ProductHoldet, position: Int) {
        holder.bindData(products.get(position))
    }


    class ProductHoldet(val binding: ProductItemBinding, val callBack: ProductCallBack) : RecyclerView.ViewHolder(binding.root),ProductCallBack {

        override fun onClick(product: ProductEntity) {
            callBack.onClick(product)
        }

        fun bindData(product: ProductEntity){
            binding.callBack = this
            binding.product  = product
        }
    }

}