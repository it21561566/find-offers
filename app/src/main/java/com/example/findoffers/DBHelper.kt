package com.example.findoffers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper (context: Context): SQLiteOpenHelper(context, "Offers", null, 1){
    override fun onCreate(p0: SQLiteDatabase?) {
        //creating the table
        p0?.execSQL("create table Offers(_id integer primary key autoincrement, ShopName Text, OfferName Text, Discount Text)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists Offers")
    }

    fun saveShopData(ShopName: String, OfferName: String, Discount: String): Boolean{
        val p0 = this.writableDatabase
        val rs = p0.rawQuery("SELECT * FROM Offers" , null)
        val cv = ContentValues()
        cv.put("ShopName", ShopName)
        cv.put("OfferName", OfferName)
        cv.put("Discount",Discount)
        val result = p0.insert("Offers", null, cv)
        rs.requery()
        if(result == -1.toLong()){
            return false
        }
        return true
    }

    fun updateShopData(ShopName: String, OfferName: String, Discount: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("ShopName", ShopName)
        cv.put("OfferName", OfferName)
        cv.put("Discount", Discount)

        val cursor: Cursor = db.rawQuery("SELECT * FROM Offers WHERE ShopName=?", arrayOf(ShopName))

        if (cursor.count > 0) {
            val result = db.update("Offers", cv, "ShopName=?", arrayOf(ShopName))
            db.close()

            return result != -1
        } else {
            db.close()

            return false
        }
    }

    fun deleteShopData(ShopName: String): Boolean { //edit this
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Offers WHERE ShopName=?", arrayOf(ShopName))

        if (cursor.count > 0) {
            val result = db.delete("Offers", "ShopName=?", arrayOf(ShopName))
            db.close()

            return result != -1
        } else {
            db.close()

            return false
        }
    }

    fun getShopData(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from Offers", null)
        return cursor
    }
}
