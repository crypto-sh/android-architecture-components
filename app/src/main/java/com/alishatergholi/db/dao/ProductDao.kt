package com.alishatergholi.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import com.alishatergholi.db.entity.ProductEntity


@Dao
interface ProductDao {

    @Query("DELETE FROM products")
    fun deleteAll()

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