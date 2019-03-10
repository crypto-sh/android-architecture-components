package com.alishatergholi.db

import android.content.Context
import androidx.annotation.VisibleForTesting
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.alishatergholi.AppExecuter
import com.alishatergholi.db.dao.CommentDao
import com.alishatergholi.db.dao.ProductDao
import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity
import com.alishatergholi.helper.LogHelper
import com.alishatergholi.utils.DateConvert


@Database(entities = arrayOf(CommentEntity::class, ProductEntity::class)  , version = 1)
@TypeConverters(DateConvert::class)
abstract class AppDataBase : RoomDatabase() {

    abstract fun commentDao() : CommentDao

    abstract fun productDao() : ProductDao

    private var mIsDatabaseCreated = MutableLiveData<Boolean>()

    companion object {

        var logHelper = LogHelper(this::class.java)

        @VisibleForTesting
        val DATABASE_NAME = "mycujoo.db"

        @Volatile
        private var INSTANCE: AppDataBase? = null

        fun getInstance(context: Context, executor: AppExecuter, testMode: Boolean): AppDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context, executor, testMode).also {
                    INSTANCE = it
                }
            }

        private fun buildDatabase(context: Context, executor: AppExecuter, testMode: Boolean): AppDataBase {
            if (testMode) {
                return Room.inMemoryDatabaseBuilder(context.applicationContext, AppDataBase::class.java).build()
            } else {
                return Room.databaseBuilder(context.applicationContext, AppDataBase::class.java, DATABASE_NAME)
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            executor.diskIO.execute {
                                val database = AppDataBase.getInstance(context,executor,testMode)
                                val products = DataGenerator.generateProducts()
                                val comments = DataGenerator.generateCommentsForProducts(products)
                                database.insertData(products,comments)
                                database.updateDataBaseCreated(context)
                            }
                        }
                    }).allowMainThreadQueries().build()
            }
        }
    }

    fun insertData(products : List<ProductEntity>, comments: List<CommentEntity>){
        this.runInTransaction {
            productDao().insertAll(products)
            commentDao().insertAll(comments)
        }
    }

    fun updateDataBaseCreated(context: Context) {
        if (context.getDatabasePath(DATABASE_NAME).exists()) {
            setDataBaseCreated()
        }
    }

    fun setDataBaseCreated() {
        mIsDatabaseCreated.postValue(true)
    }

    fun getDataBaseCreated() : LiveData<Boolean> {
        return mIsDatabaseCreated
    }

}