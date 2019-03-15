package com.alishatergholi.helper

import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity


interface CommentCallBack {
    fun onClick(comment : CommentEntity)
}

interface ProductCallBack {
    fun onClick(product : ProductEntity)
}

