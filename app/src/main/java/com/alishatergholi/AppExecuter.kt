package com.alishatergholi

import android.os.Handler
import android.os.Looper
import java.util.concurrent.Executor
import java.util.concurrent.Executors


class AppExecuter constructor(var diskIO: Executor, var networkIO: Executor, var mainThread: Executor) {

    constructor() : this(
        Executors.newSingleThreadExecutor(),
        Executors.newFixedThreadPool(3),
        MainThreadExecuter()
    )

    class MainThreadExecuter : Executor {
        var handler = Handler(Looper.getMainLooper())

        override fun execute(command: Runnable?) {
            handler.post(command)
        }

    }
}