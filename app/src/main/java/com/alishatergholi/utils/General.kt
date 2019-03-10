package com.alishatergholi.utils

class General {

    companion object {
        @JvmStatic
        fun methods() : General = General()
    }

    fun StringIsEmptyOrNull(string: String?): Boolean {
        try {
            return string == null || string.isEmpty() || string == "null"
        } catch (ex: Exception) {
            return false
        }

    }

}