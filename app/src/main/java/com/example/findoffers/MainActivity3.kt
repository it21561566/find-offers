package com.example.findoffers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class MainActivity3 : AppCompatActivity() {

    private lateinit var shopname: EditText
    private lateinit var shopCity: EditText
    private lateinit var shopType: EditText
    private lateinit var delete: ImageView
    private lateinit var edit: ImageView

    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        shopname = findViewById(R.id.editTextTextShopName)
        shopCity = findViewById(R.id.editTextTextShopCity2)
        shopType = findViewById(R.id.editTextTextShopCity3)
        delete = findViewById(R.id.imageView2)
        edit = findViewById(R.id.imageView3)

        db = DBHelper(this)

        shopname.setText(intent.getStringExtra("ShopName"))
        shopCity.setText(intent.getStringExtra("ShopCity"))
        shopType.setText(intent.getStringExtra("ShopType"))

        edit.setOnClickListener{
            val shopNameString = shopname.text.toString()
            val shopCityString = shopCity.text.toString()
            val shopTypeString = shopType.text.toString()

            val updateData = db.updateShopData(shopNameString, shopCityString, shopTypeString)

            if (updateData == true) {
                Toast.makeText(this, "Data Updated", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data Not Updated", Toast.LENGTH_SHORT).show()
            }
        }

        delete.setOnClickListener{
            val shopNameString = shopname.text.toString()

            val deleteData = db.deleteShopData(shopNameString)

            if (deleteData == true) {
                Toast.makeText(this, "Data Deleted", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Data Not Deleted", Toast.LENGTH_SHORT).show()
            }
        }
    }
}