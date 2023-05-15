package com.example.findoffers

import android.content.Intent
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var button: FloatingActionButton
    private lateinit var button2: FloatingActionButton
    private lateinit var dbh: DBHelper
    private lateinit var dataArray: ArrayList<DataList>
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        button = findViewById(R.id.floatingActionButton)
        button2 = findViewById(R.id.floatingActionButton2)

        button.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity6::class.java)
            startActivity(intent)
        }

        dbh = DBHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        displayShopData()
    }

    private fun displayShopData() {
        val cursor: Cursor? = dbh.getText()
        dataArray = ArrayList()
        while (cursor?.moveToNext() == true) {
            val shopName = cursor.getString(1)
            val shopCity = cursor.getString(2)
            val shopType = cursor.getString(3)
            dataArray.add(DataList(shopName, shopCity, shopType))
        }
        adapter = MyAdapter(dataArray)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, MainActivity3::class.java).apply {
                    putExtra("ShopName", dataArray[position].ShopName)
                    putExtra("ShopCity", dataArray[position].ShopCity)
                    putExtra("ShopType", dataArray[position].ShopType)
                }
                startActivity(intent)
            }
        })
    }
}