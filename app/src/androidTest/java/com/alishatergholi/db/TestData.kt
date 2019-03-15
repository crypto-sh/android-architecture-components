package com.alishatergholi.db

import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity
import java.util.*

class TestData {

    companion object {

        var product_Entity = ProductEntity(1,"name","description",100)
        var product_Entity2 = ProductEntity(2,"name","description",200)
        var products = Arrays.asList(product_Entity, product_Entity2)

        var comment_Entity = CommentEntity(1, product_Entity.id,"comment 1",Date())
        var comment_Entity2 = CommentEntity(2, product_Entity2.id,"comment 1",Date())
        var comments = Arrays.asList(comment_Entity, comment_Entity2)
    }

}