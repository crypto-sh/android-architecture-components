package com.alishatergholi.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
class ProductEntity {

    @PrimaryKey
    var id      : Int = 0
    var name    : String? = null
    var description: String? = null
    var price   : Int = 0

}