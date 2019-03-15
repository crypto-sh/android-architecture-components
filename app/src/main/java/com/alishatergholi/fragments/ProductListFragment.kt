package com.alishatergholi.fragments


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.alishatergholi.BaseApp
import com.alishatergholi.R
import com.alishatergholi.adapter.ProductAdapter
import com.alishatergholi.databinding.FragmentProductListBinding
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.helper.ProductCallBack
import com.alishatergholi.viewModel.ProductListViewModel


/**
 * A simple [BaseFragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [ProductListFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProductListFragment : BaseFragment() {

    var callBack : ProductCallBack?          = null
    var viewModel: ProductListViewModel?     = null
    var binding: FragmentProductListBinding? = null

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductListFragment.
         */
        @JvmStatic
        fun newInstance() = ProductListFragment()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ProductCallBack){
            callBack = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        callBack = null
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = setContentView(inflater, container!!, R.layout.fragment_product_list)
        initRecycler()
        return binding!!.root
    }

    fun initRecycler() {
        viewModel = ViewModelProviders.of(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T {
                val repository = (activity!!.application as BaseApp).respository
                return ProductListViewModel(repository!!) as T
            }
        }).get(ProductListViewModel::class.java)
        var adapter = ProductAdapter(object : ProductCallBack {
            override fun onClick(product: ProductEntity) {
                callBack?.onClick(product)
            }
        })
        viewModel!!.getProduct().observe(this, Observer {
            if (it != null)
                adapter.setProducts(it)

        })
        binding!!.productsList.adapter = adapter
    }



}
