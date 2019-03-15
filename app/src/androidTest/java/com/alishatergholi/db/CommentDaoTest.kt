package com.alishatergholi.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import com.alishatergholi.db.dao.CommentDao
import com.alishatergholi.db.dao.ProductDao
import com.alishatergholi.utils.LiveDataTestUtil
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CommentDaoTest {

    @Rule @JvmField
    var instant = InstantTaskExecutorRule()

    var database   : AppDataBase?  = null

    var commentDao : CommentDao?   = null
    var productDao : ProductDao?   = null

    @Before
    fun initDatabase() {
        database   = AppDataBase.getInstance(InstrumentationRegistry.getInstrumentation().context,null,true)
        productDao = database!!.productDao()
        commentDao = database!!.commentDao()
    }

    @After
    fun closeDatabase(){
        database!!.close()
    }

    @Test
    fun getCommentwhenNoCommentInserted(){
        val comments = LiveDataTestUtil.getValue(commentDao!!.loadComments(TestData.comment_Entity.productId))
        assertTrue(comments.isEmpty())
    }

    @Test
    fun getCommentAfterInserted(){
        productDao!!.insertAll(TestData.products)
        commentDao!!.insertAll(TestData.comments)
        val comments = LiveDataTestUtil.getValue(commentDao!!.loadComments(TestData.product_Entity.id))
        assertEquals(comments.size, 1)
    }

}