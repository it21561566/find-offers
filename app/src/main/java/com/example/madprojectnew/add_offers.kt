package com.example.madprojectnew

import android.content.ContentValues
import android.content.Intent
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class add_offers : AppCompatActivity() {

    private lateinit var Start_Date:EditText
    private lateinit var End_Date:EditText
    private lateinit var Discount:EditText
    private lateinit var Description:EditText
    private lateinit var Email:EditText
    private lateinit var submitbtn:Button

    private lateinit var db :DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_offers)


        Start_Date =findViewById(R.id.editTextDate3)
        End_Date =findViewById(R.id.editTextDate)
        Discount =findViewById(R.id.editTextTextPersonName)
        Description =findViewById(R.id.editTextTextPersonName5)
        Email=findViewById(R.id.editTextTextPersonName2)
        submitbtn =findViewById(R.id.submitbtn)

        db = DBHelper(this)

        submitbtn.setOnClickListener {
            val  Start_Date =Start_Date.text.toString()
            val End_Date =End_Date.text.toString()
            val Discount =Discount.text.toString()
            val Description =Description.text.toString()
            val Email=Email.text.toString()
            val submitbtn = db.savediscount(Start_Date, End_Date, Discount, Description, Email)

            if (TextUtils.isEmpty(Start_Date) || TextUtils.isEmpty(End_Date) || TextUtils.isEmpty(Discount) || TextUtils.isEmpty(Description) || TextUtils.isEmpty(Email)) {
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()

            } else {
                if (submitbtn == true) {
                    Toast.makeText(this, "Discount added successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, profile::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this, "discount exist", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}