package com.example.ethernetshop


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_edit_item.*
import java.time.LocalDate
import java.time.Year

class EditItemActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_item)

        val product = dataBase.find { it.id == intent.getIntExtra("id", 0) } ?: Product(
            (dataBase.maxBy { it.id }?.id ?: 0) + 1
        )
        val name = findViewById<EditText>(R.id.name)
        name.setText(product.name)
        val category = findViewById<EditText>(R.id.category)
        category.setText(product.category)
        val description = findViewById<EditText>(R.id.description)
        description.setText(product.description)
        val price = findViewById<EditText>(R.id.price)
        price.setText(product.price)

        val image = findViewById<ImageView>(R.id.imageView)

        if (product.image != null)
            image.setImageDrawable(product.image)
        image.setOnClickListener {
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            intent.resolveActivity(packageManager)
            startActivityForResult(intent, photoCode)
        }

        val buttonSave = findViewById<Button>(R.id.buttonSave)
        buttonSave.setOnClickListener {
            product.name = name.text.toString()
            product.category = category.text.toString()
            product.description = description.text.toString()
            product.price = price.text.toString()


            val i = dataBase.indexOfFirst { it.id == product.id }
            if (i != -1)
                dataBase[i] = product
            else
                dataBase.add(product)
            finish()
        }
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == photoCode && resultCode == Activity.RESULT_OK) {
            if (data != null)
            {
                val bitmap =  data.extras?.get("data") as Bitmap
                findViewById<ImageView>(R.id.imageView).setImageBitmap(bitmap)
            }
        }
    }

    companion object{
        const val photoCode = 0
    }
}