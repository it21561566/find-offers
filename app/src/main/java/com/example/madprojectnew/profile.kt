package com.example.madprojectnew

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class profile : AppCompatActivity() {
    private lateinit var Addbtn: Button
    private lateinit var recyclerView: RecyclerView
    lateinit var dbh:DBHelper
    private lateinit var newArrayList: ArrayList<datalistDisc>
    private lateinit var adapter: myAdapter
    private lateinit var logoutbtn: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        recyclerView = findViewById(R.id.recycler)
        Addbtn = findViewById(R.id.button4)
        logoutbtn = findViewById(R.id.button3)

        Addbtn.setOnClickListener {
            val intent = Intent(applicationContext, add_offers::class.java)
            startActivity(intent)
        }

        dbh = DBHelper(this)
        recyclerView.layoutManager= LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        displayDiscount()

        logoutbtn.setOnClickListener {
            val inten = Intent(applicationContext,login::class.java)
            startActivity(inten)
        }

    }

    private fun displayDiscount() {
        val newcursor:Cursor? =dbh.getdisc()
        newArrayList = ArrayList<datalistDisc>()
        while (newcursor!!.moveToNext()){
            val startDate = newcursor.getString(0)
            val endDate = newcursor.getString(1)
            val discount = newcursor.getString(2)
            val description = newcursor.getString(3)
//            val email = newcursor.getString(4)
            newArrayList.add(datalistDisc(startDate,endDate,discount,description))
        }
        adapter=myAdapter(newArrayList)
        recyclerView.adapter = adapter
        adapter.onItemClickListner(object :myAdapter.onItemClickListner{
            override fun onItemClick(position: Int) {
                val intent=Intent(this@profile,oneoffer::class.java)
                intent.putExtra("startDate",newArrayList[position].startDate)
                intent.putExtra("endDate",newArrayList[position].endDate)
                intent.putExtra("discount",newArrayList[position].discount)
                intent.putExtra("description",newArrayList[position].description)
                startActivity(intent)
            }

        })
    }
}
