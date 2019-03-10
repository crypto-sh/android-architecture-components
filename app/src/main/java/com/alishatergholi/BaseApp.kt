package com.alishatergholi

import android.app.Application
import com.alishatergholi.db.AppDataBase
import com.alishatergholi.repository.DataRepository


class BaseApp : Application() {

    var appExecuter                   = AppExecuter()

    var database    : AppDataBase?    =  null

    var respository : DataRepository? = null

    override fun onCreate() {
        super.onCreate()
        database    = AppDataBase.getInstance(applicationContext, appExecuter,false)
        respository = DataRepository.getInstance(database!!)
    }

}