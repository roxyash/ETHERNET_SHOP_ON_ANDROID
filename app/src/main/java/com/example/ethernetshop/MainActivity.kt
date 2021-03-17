package com.example.ethernetshop

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private val adapter = MyArrayAdapter(this)

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        dataBase.add(Product(1))
        dataBase.add(Product(2))
        dataBase.add(Product(3))

        val i = dataBase[1]
        i.name = "test"
        i.category = "testik"
        i.description = "testik"
        i.price = "testik"
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        val buttonAdd = findViewById<Button>(R.id.buttonAdd)
        val buttonDelete = findViewById<Button>(R.id.buttonDelete)

        buttonAdd.setOnClickListener {
            val intent = Intent(this,EditItemActivity::class.java)
            startActivity(intent)
        }

        buttonDelete.setOnClickListener {
            val label = TextView(this)
            label.text = "Введите id"
            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.addView(label)
            layout.addView(editText)
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(layout)
            dialogBuilder.setPositiveButton("OK") { _, _ -> dataBase.removeIf { it.id == editText.text.toString().toIntOrNull() }}
            dialogBuilder.setNegativeButton("Cancel") { _, _ -> }
            val dialog = dialogBuilder.create()
            dialog.show()
        }

        val buttonSort = findViewById<Button>(R.id.buttonSort)
        buttonSort.setOnClickListener {
            dataBase.sortBy { it.name }
            adapter.notifyDataSetChanged()
        }

        val buttonFind = findViewById<Button>(R.id.buttonFind)
        buttonFind.setOnClickListener {
            val label = TextView(this)
            label.text = "Введите id"
            val editText = EditText(this)
            editText.inputType = InputType.TYPE_CLASS_NUMBER
            val layout = LinearLayout(this)
            layout.orientation = LinearLayout.VERTICAL
            layout.addView(label)
            layout.addView(editText)
            val dialogBuilder = AlertDialog.Builder(this)
            dialogBuilder.setView(layout)
            dialogBuilder.setPositiveButton("OK") { _, _ -> if (dataBase.find { it.id == editText.text.toString().toIntOrNull() } != null)
            {
                val intent = Intent(this,EditItemActivity::class.java)
                intent.putExtra("id",editText.text.toString().toInt())
                startActivity(intent)
            }}
            dialogBuilder.setNegativeButton("Cancel") { _, _ -> }
            val dialog = dialogBuilder.create()
            dialog.show()
        }
    }


    override fun onRestart() {
        adapter.notifyDataSetChanged()
        super.onRestart()
    }
}