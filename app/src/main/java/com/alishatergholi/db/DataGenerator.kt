package com.alishatergholi.db

import com.alishatergholi.db.entity.CommentEntity
import com.alishatergholi.db.entity.ProductEntity
import java.util.*
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class DataGenerator {

    companion object {
        val FIRST = arrayOf("Special edition", "New", "Cheap", "Quality", "Used")
        val SECOND = arrayOf("Three-headed Monkey", "Rubber Chicken", "Pint of Grog", "Monocle")
        val DESCRIPTION = arrayOf("is finally here", "is recommended by Stan S. Stanman", "is the best sold product on Mêlée Island", "is \uD83D\uDCAF", "is ❤️", "is fine")
        val COMMENTS = arrayOf("Comment 1", "Comment 2", "Comment 3", "Comment 4", "Comment 5", "Comment 6")

        fun generateProducts() : List<ProductEntity> {
            var products = ArrayList<ProductEntity>(FIRST.size * SECOND.size)
            for (row in 0 until FIRST.size){
                for (column in 0 until SECOND.size){
                    var product = ProductEntity()
                    product.name = FIRST.get(row) + SECOND.get(column)
                    product.description = product.name + DESCRIPTION.get(column)
                    product.price = Random.nextInt(240)
                    product.id = FIRST.size * row + column + 1
                    products.add(product)
                }
            }
            return products
        }

        fun generateCommentsForProducts(products : List<ProductEntity>) : List<CommentEntity> {
            var comments = ArrayList<CommentEntity>()
            for (product in products){
                val commentNum =  Random.nextInt(5) + 1
                for (index in 0 until commentNum){
                    var comment = CommentEntity()
                    comment.productId = product.id
                    comment.text      = COMMENTS.get(index) + " for " + product.name
                    comment.postedAt  = Date(System.currentTimeMillis() - TimeUnit.DAYS.toMillis((commentNum  - index).toLong()) + TimeUnit.HOURS.toMillis(index.toLong()))
                    comments.add(comment)
                }
            }
            return comments
        }
    }
}