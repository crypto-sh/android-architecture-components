package com.alishatergholi.viewModel

import android.app.Application
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alishatergholi.BaseApp
import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.repository.DataRepository

class ProductViewModel(repository: DataRepository, productId: Int) : ViewModel() {

    var productId        : Int = 0
    var productObservable: LiveData<ProductEntity>?         = null
    var commentObservable: LiveData<List<CommentEntity>>?   = null
    var product          = ObservableField<ProductEntity>()

    init {
        this.productId         = productId
        this.productObservable = repository.loadProduct(productId)
        this.commentObservable = repository.getComments(productId)
    }

    class Factory(private val application: Application, private val productId: Int) :

        ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            val repository = (application as? BaseApp)?.respository


            return ProductViewModel(repository!!, productId) as T
        }
    }


}