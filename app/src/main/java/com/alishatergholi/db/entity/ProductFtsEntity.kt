package com.alishatergholi.db.entity

import androidx.room.Entity


//@Fts4(contentEntity = ProductEntity::class)
@Entity(tableName = "productsFts")
class ProductFtsEntity {

    var name        : String = ""
    var description : String = ""

}