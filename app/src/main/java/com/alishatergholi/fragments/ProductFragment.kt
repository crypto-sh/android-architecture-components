package com.alishatergholi.fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.alishatergholi.R
import com.alishatergholi.adapter.CommentAdapter
import com.alishatergholi.databinding.FragmentProductBinding
import com.alishatergholi.viewModel.ProductViewModel


private const val KEY_PRODUCT_ID = "product_id"

/**
 * A simple [BaseFragment] subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class ProductFragment : BaseFragment() {

    private var productId : Int = 0

    private var adapter : CommentAdapter?         = null

    private var binding : FragmentProductBinding? = null

    companion object {

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment ProductFragment.
         */
        @JvmStatic
        fun newInstance() = ProductFragment()


        @JvmStatic
        fun newInstance(productId : Int) = ProductFragment().apply {
            arguments = Bundle().apply {
                putInt(KEY_PRODUCT_ID,productId)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (arguments != null){
            productId = arguments!!.getInt(KEY_PRODUCT_ID)
        }
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
        var viewModel = ViewModelProviders.of(this, ProductViewModel.Factory(activity!!.application, productId)).get(ProductViewModel::class.java)
        binding!!.productViewModel = viewModel

        subscribeToModel(viewModel)
    }

    fun subscribeToModel(viewModel: ProductViewModel){
        viewModel.commentObservable!!.observe(this, Observer {
            adapter!!.setComments(it)
        })
        viewModel.productObservable!!.observe(this, Observer {
            if (it != null) {
                viewModel.product.set(it)
                binding!!.isLoading = false
            }else{
                binding!!.isLoading = true
            }
        })
    }
}
