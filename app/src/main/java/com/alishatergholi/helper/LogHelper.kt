package com.alishatergholi.helper

import android.util.Log
import com.alishatergholi.BuildConfig
import com.alishatergholi.utils.General

class LogHelper(cls: Class<*>) {

    private var LOG_TAG = ""

    private val MAX_LOG_TAG_LENGTH = 23

    init {
        LOG_TAG = cls.simpleName
        if (LOG_TAG.length > MAX_LOG_TAG_LENGTH) {
            LOG_TAG = LOG_TAG.substring(0, MAX_LOG_TAG_LENGTH - 1)
        }
    }

    fun v(messages: String) {
        // Only log VERBOSE if build type is DEBUG
        if (BuildConfig.enableDebugLogging) {
            log(LOG_TAG, Log.VERBOSE, null, messages);
        }
    }

    fun d(messages: String) {
        // Only log DEBUG if build type is DEBUG
        if (BuildConfig.enableDebugLogging) {
            log(LOG_TAG, Log.DEBUG, null, messages);
        }
    }

    fun d(method: String, messages: String) {
        // Only log DEBUG if build type is DEBUG
        if (BuildConfig.enableDebugLogging) {
            log(LOG_TAG, Log.DEBUG, null, "$method : $messages");
        }
    }

    fun i(messages: String) {
        log(LOG_TAG, Log.INFO, null, messages);
    }

    fun w(messages: String) {
        log(LOG_TAG, Log.WARN, null, messages);
    }

    fun w(t: Throwable, messages: String) {
        log(LOG_TAG, Log.WARN, t, messages);
    }

    fun e(e: Exception) {
        log(LOG_TAG, Log.ERROR, null, e.message!!)
    }

    fun e(method: String, e: Exception) {
        log(LOG_TAG, Log.ERROR, null, "$method   :  $e.message")
    }

    fun e(e: String) {
        log(LOG_TAG, Log.ERROR, null, e);
    }

    fun e(method: String, e: String) {
        log(LOG_TAG, Log.ERROR, null, "$method : $e")
    }

    fun e(t: Throwable, messages: String) {
        log(LOG_TAG, Log.ERROR, t, messages)
    }

    private fun log(tag: String, level: Int, t: Throwable?, messages: String) {
        var msg: String
        if (General.methods().StringIsEmptyOrNull(messages)) {
            if (t == null) {
                msg = messages
                Log.println(level, tag, msg)
            } else {
                val sb = StringBuilder()
                sb.append(messages)
                sb.append("\n").append(Log.getStackTraceString(t))
                msg = sb.toString()
                Log.println(level, tag, msg)
            }
        }
    }
}