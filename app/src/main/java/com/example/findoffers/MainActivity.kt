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
    lateinit var dbh: DBHelper
    private lateinit var newArray: ArrayList<DataList>
    private lateinit var adapter: MyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        button = findViewById(R.id.floatingActionButton)

        button.setOnClickListener{
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        dbh = DBHelper(this)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        displayShopData()
    }

    private fun displayShopData() {
        val cursor: Cursor? = dbh.getShopData() // Updated the method name to `getShopData()`
        newArray = ArrayList<DataList>()
        cursor?.let {
            while (it.moveToNext()) {
                val shopName = it.getString(1)
                val offerName = it.getString(2)
                val discount = it.getString(3)
                newArray.add(DataList(shopName, offerName, discount))
            }
        }
        adapter = MyAdapter(newArray)
        recyclerView.adapter = adapter
        adapter.setOnItemClickListener(object : MyAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val intent = Intent(this@MainActivity, MainActivity3::class.java).apply {
                    putExtra("ShopName", newArray[position].ShopName)
                    putExtra("OfferName", newArray[position].OfferName)
                    putExtra("Discount", newArray[position].Discount)
                }
                startActivity(intent)
            }
        })
    }
}


