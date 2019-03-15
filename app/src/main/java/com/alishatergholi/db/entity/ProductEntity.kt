package com.alishatergholi.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "products")
class ProductEntity {

    constructor(){

    }

    constructor(id: Int, name: String?, description: String?, price: Int) {
        this.id = id
        this.name = name
        this.description = description
        this.price = price
    }

    @PrimaryKey
    var id      : Int = 0
    var name    : String? = null
    var description: String? = null
    var price   : Int = 0

}