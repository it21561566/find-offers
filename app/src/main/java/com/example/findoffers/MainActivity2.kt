package com.example.findoffers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.Toast
import com.google.android.material.textfield.TextInputEditText

class MainActivity2 : AppCompatActivity() {

    private lateinit var shopName: TextInputEditText
    private lateinit var shopCity: TextInputEditText
    private lateinit var shopType: TextInputEditText
    private lateinit var button: Button
    private lateinit var db: DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        shopName = findViewById(R.id.textEdit1)
        shopCity = findViewById(R.id.textEdit2)
        shopType = findViewById(R.id.textEdit3)
        button = findViewById(R.id.saveButton)

        db = DBHelper(this)

        button.setOnClickListener {
            val shopNameString = shopName.text.toString()
            val shopCityString = shopCity.text.toString()
            val shopTypeString = shopType.text.toString()
            val savedData = db.saveShopData(shopNameString, shopCityString, shopTypeString)

            if (TextUtils.isEmpty(shopNameString) || TextUtils.isEmpty(shopCityString) || TextUtils.isEmpty(shopTypeString)) {
                Toast.makeText(this, "Please add shopName, shopCity, and shopType", Toast.LENGTH_SHORT).show()

            } else {
                if (savedData) {
                    Toast.makeText(this, "Data saved", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "Data already exists", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}