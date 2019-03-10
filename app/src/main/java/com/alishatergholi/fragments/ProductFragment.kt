package com.alishatergholi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alishatergholi.R
import com.alishatergholi.adapter.CommentAdapter
import com.alishatergholi.databinding.FragmentProductBinding
import com.alishatergholi.viewModel.ProductViewModel



/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProductFragment : BaseFragment() {

    private var adapter : CommentAdapter?         = null

    private var binding : FragmentProductBinding? = null

    private val KEY_PRODUCT_ID = "product_id"

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductFragment.
         */
        @JvmStatic
        fun newInstance() = ProductFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding =  setContentView(inflater,container!!,R.layout.fragment_product)
        adapter = CommentAdapter()
        binding!!.recyclerView.adapter = adapter
        return binding!!.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //arguments!!.getInt(KEY_PRODUCT_ID)
        var viewModel = ViewModelProviders.of(this, ProductViewModel.Factory(activity!!.application, 1)).get(ProductViewModel::class.java)
        binding!!.viewModel = viewModel
        subscribeToModel(viewModel)
    }

    fun subscribeToModel(viewModel: ProductViewModel){
        viewModel.commentObservable!!.observe(this, Observer {
            adapter!!.setComments(it)
        })
        viewModel.productObservable!!.observe(this, Observer {
            viewModel.product.set(it)
        })
    }
}
