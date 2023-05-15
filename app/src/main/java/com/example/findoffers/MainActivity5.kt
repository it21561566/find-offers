package com.example.findoffers

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity5 : AppCompatActivity() {

    private lateinit var recyclerView2: RecyclerView
    private lateinit var button3: FloatingActionButton
    private lateinit var db: DBHelper
    private lateinit var data1Array: ArrayList<shoptype>
    private lateinit var adapter: shopAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        recyclerView2 = findViewById(R.id.recyclerView)
        button3 = findViewById(R.id.floatingActionButton)
        db = DBHelper(this)

        button3.setOnClickListener {
            val intent = Intent(this, OffersCalculations::class.java)
            startActivity(intent)
        }

        recyclerView2.layoutManager = LinearLayoutManager(this)
        recyclerView2.setHasFixedSize(true)

        displayshopTypes()

        adapter = shopAdapter(data1Array)
        recyclerView2.adapter = adapter
    }

    private fun displayshopTypes() {
        val cursor1: Cursor? = db.getText()
        data1Array = ArrayList()
        while (cursor1?.moveToNext() == true) {
            val shopName = cursor1.getString(1)
            data1Array.add(shoptype(shopName))
        }
    }
}