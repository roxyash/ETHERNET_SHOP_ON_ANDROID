package com.example.ethernetshop


import android.graphics.drawable.Drawable

val dataBase = Database(mutableListOf())


class Product(val id:Int)
{
    var image: Drawable? = null
    var name:String = ""
    var category: String = ""
    var description:String =  ""
    var price: String = ""

    override fun hashCode() = id

    override fun equals(other: Any?): Boolean {
        if (this === other) return true

        other as Product

        if (this.id != other.id)
            return false
        return true
    }
}

class Database(private val items:MutableList<Product>) : MutableList<Product> by items
{
    override fun add(element: Product): Boolean {
        for (i in this)
        {
            if (element == i)
                return false
        }
        return items.add(element)
    }
}