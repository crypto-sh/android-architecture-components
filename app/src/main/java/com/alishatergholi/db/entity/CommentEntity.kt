package com.alishatergholi.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*


@Entity(tableName = "comments")
class CommentEntity {

    constructor(){}

    constructor(id: Int, productId: Int, text: String, postedAt: Date?) {
        this.id = id
        this.productId = productId
        this.text = text
        this.postedAt = postedAt
    }


    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
    var productId : Int = 0
    var text : String = ""
    var postedAt : Date? = null



}