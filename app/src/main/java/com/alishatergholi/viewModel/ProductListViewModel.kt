package com.alishatergholi.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.repository.DataRepository

class ProductListViewModel(val repository : DataRepository) : ViewModel() {

    var products = MediatorLiveData<List<ProductEntity>>()

    init {
        products.value = null
        var loadedData = repository.getProducts()
        products.addSource(loadedData) {
            products.value = it
        }
    }

    public fun getProduct() : LiveData<List<ProductEntity>> {
        return products
    }
    public fun searchProduct(query : String) : LiveData<List<ProductEntity>>? {
        return repository.searchProduct(query)
    }

}