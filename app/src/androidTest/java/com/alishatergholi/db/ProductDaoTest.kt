package com.alishatergholi.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import com.alishatergholi.db.dao.ProductDao
import com.alishatergholi.utils.LiveDataTestUtil
import org.junit.Assert.*
import com.alishatergholi.db.TestData.Companion.product_Entity
import com.alishatergholi.db.TestData.Companion.products
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters


@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ProductDaoTest {

    @Rule @JvmField
    var instant = InstantTaskExecutorRule()

    var database   : AppDataBase?  = null
    var productDao : ProductDao?   = null

    @Before
    fun initDatabase() {
        database = AppDataBase.getInstance(InstrumentationRegistry.getInstrumentation().context,null,true)
        productDao = database!!.productDao()
    }

    @After
    fun closeDatabase(){
        database!!.close()
    }

    @Test
    fun step1_getProductWhenNoProductInserted(){
        productDao!!.deleteAll()
        val items =  LiveDataTestUtil.getValue(productDao!!.loadAllProducts())
        assertTrue(items.isEmpty())
    }

    @Test
    fun step2_getProductAfterInserted(){
        productDao!!.insertAll(products)
        val items =  LiveDataTestUtil.getValue(productDao!!.loadAllProducts())
        assertTrue(items.size == products.size)
    }

    @Test
    fun step3_getProductById(){
        val product            =  productDao!!.loadProductSync(product_Entity.id)
        assertEquals(product.id             , product_Entity.id)
        assertEquals(product.name           , product_Entity.name)
        assertEquals(product.description    , product_Entity.description)
        assertEquals(product.price          , product_Entity.price)
    }

}