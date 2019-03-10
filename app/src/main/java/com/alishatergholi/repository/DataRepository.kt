package com.alishatergholi.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.Observer
import com.alishatergholi.db.AppDataBase
import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity


class DataRepository {

    var database : AppDataBase? = null

    var products = MediatorLiveData<List<ProductEntity>>()

    companion object {

        @Volatile var INSTANCE : DataRepository? = null

        fun getInstance(database: AppDataBase) : DataRepository? {
            if (INSTANCE == null){
                INSTANCE ?: synchronized(this) {
                    INSTANCE = DataRepository(database).also {
                        INSTANCE = it
                    }
                }
            }
            return INSTANCE
        }
    }

    constructor(database: AppDataBase) {
        this.database = database
        this.products.addSource(database.productDao().loadAllProducts(), Observer {

            if (database.getDataBaseCreated().value != null) {
                products.value = it
            }
        })
    }

    fun getProducts(): LiveData<List<ProductEntity>> {
        return products
    }

    fun loadProduct(productId : Int) : LiveData<ProductEntity> {
        return database!!.productDao().loadProduct(productId)
    }

    fun getComments(productId: Int): LiveData<List<CommentEntity>>? {
        return database!!.commentDao().loadComments(productId)
    }
}