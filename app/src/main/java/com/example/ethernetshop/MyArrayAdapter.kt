package com.example.ethernetshop

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyArrayAdapter( private val activivty: MainActivity) : RecyclerView.Adapter<MyArrayAdapter.MyArrayHolder>(){
    class MyArrayHolder(val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyArrayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view,parent,false)
        return MyArrayHolder(view)
    }

    override fun getItemCount() = dataBase.size

    override fun onBindViewHolder(holder: MyArrayHolder, position: Int) {
        val id = holder.view.findViewById<TextView>(R.id.id)
        id.text = dataBase[position].id.toString()
        val image = holder.view.findViewById<ImageView>(R.id.images)
        image.setImageDrawable(dataBase[position].image)
        val name = holder.view.findViewById<TextView>(R.id.names)
        name.text = dataBase[position].name
        holder.view.setOnClickListener {
            val intent = Intent(activivty,EditItemActivity::class.java)
            intent.putExtra("id",dataBase[position].id)
            activivty.startActivity(intent)
        }
    }

}