package com.example.madprojectnew

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class login : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password:EditText
    private lateinit var loginbtn:Button
    private lateinit var  signbtn:Button
    private lateinit var db :DBHelper
//    private lateinit var txt:EditText


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        email = findViewById(R.id.editTextTextPersonName)
        password=findViewById(R.id.editTextTextPassword)
        loginbtn = findViewById(R.id.button)
        signbtn=findViewById(R.id.button2)
//        txt=findViewById(R.id.textView6)

        db = DBHelper(this)

        loginbtn.setOnClickListener {
            val email = email.text.toString()
            val password = password.text.toString()

            if(TextUtils.isEmpty(email)||TextUtils.isEmpty(password)){
                Toast.makeText(this, "Fill all the fields", Toast.LENGTH_SHORT).show()

            }else{
                val checkuserpass = db.checkuserpass(email,password)
                if(checkuserpass==true){
                    Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show()
                    val intent = Intent(applicationContext, profile::class.java)
                    startActivity(intent)
                }else{
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()

                }
            }
        }
        signbtn.setOnClickListener {
            val inten = Intent(applicationContext,MainActivity::class.java)
            startActivity(inten)
        }
    }
}