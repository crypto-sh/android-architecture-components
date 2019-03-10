package com.alishatergholi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alishatergholi.db.entity.ProductEntity


@Dao
interface ProductDao {

    @Query("SELECT * FROM products")
    fun loadAllProducts() : LiveData<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(products : List<ProductEntity>)

    @Query("select * from products where id = :productId")
    fun loadProduct(productId : Int) : LiveData<ProductEntity>

    @Query("select * from products where id = :productId")
    fun loadProductSync(productId : Int) : ProductEntity

//    @Query("SELECT products.* FROM products JOIN productsFts ON (products.id = productsFts.rowid) WHERE productsFts MATCH :query")
//    fun searchAllProducts(query : String) : LiveData<List<ProductEntity>>
}