package com.example.madprojectnew

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class oneoffer : AppCompatActivity() {


    private lateinit var Start_Date: EditText
    private lateinit var End_Date: EditText
    private lateinit var Discount: EditText
    private lateinit var Description: EditText
    private lateinit var edtbtn:ImageView
    private lateinit var delbtn:ImageView
    private lateinit var db:DBHelper

    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_oneoffer)


        Start_Date =findViewById(R.id.editTextDate3)
        End_Date =findViewById(R.id.editTextDate)
        Discount =findViewById(R.id.editTextTextPersonName8)
        Description =findViewById(R.id.editTextTextPersonName9)
        edtbtn=findViewById(R.id.imageView3)
        delbtn=findViewById(R.id.imageView2)

        db= DBHelper(this)

        Start_Date.setText(intent.getStringExtra("startDate"))
        End_Date.setText(intent.getStringExtra("endDate"))
        Discount.setText(intent.getStringExtra("discount"))
        Description.setText(intent.getStringExtra("description"))

        delbtn.setOnClickListener{

            val Description =Description.text.toString()
            val deletedata = db.deldisc(Description)

                if (deletedata == true) {
                    Toast.makeText(this, "Discount deleted successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, profile::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "discount delete error", Toast.LENGTH_SHORT).show()
                }
            }
        edtbtn.setOnClickListener{
            val  Start_Date =Start_Date.text.toString()
            val End_Date =End_Date.text.toString()
            val Discount =Discount.text.toString()
            val Description =Description.text.toString()
            val updatedata = db.updatedisc(Start_Date, End_Date, Discount, Description)

            if (updatedata == true) {
                Toast.makeText(this, "Discount updated successfully", Toast.LENGTH_SHORT).show()
                val intent = Intent(applicationContext, profile::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "discount update error", Toast.LENGTH_SHORT).show()
            }

        }

        }

    }