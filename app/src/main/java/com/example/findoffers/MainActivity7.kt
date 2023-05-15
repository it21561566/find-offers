package com.example.findoffers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity7 : AppCompatActivity() {

    private lateinit var button:Button
    private lateinit var button1:Button
    private lateinit var button2:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        button = findViewById(R.id.button)
        button1 = findViewById(R.id.button5)
        button2= findViewById(R.id.button2)
        button.setOnClickListener{
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
        button1.setOnClickListener{
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity5::class.java)
            startActivity(intent)
        }
    }
}