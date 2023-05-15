package com.example.findoffers

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context) : SQLiteOpenHelper(context, "Shop", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("CREATE TABLE Shop (_id INTEGER PRIMARY KEY AUTOINCREMENT, ShopName TEXT, ShopCity TEXT, ShopType TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Shop")
        onCreate(db)
    }

    fun saveShopData(ShopName: String, ShopCity: String, ShopType: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("ShopName", ShopName)
        cv.put("ShopCity", ShopCity)
        cv.put("ShopType", ShopType)

        val result = db.insert("Shop", null, cv)
        db.close()

        return result != -1L
    }

    fun updateShopData(ShopName: String, ShopCity: String, ShopType: String): Boolean {
        val db = this.writableDatabase
        val cv = ContentValues()

        cv.put("ShopName", ShopName)
        cv.put("ShopCity", ShopCity)
        cv.put("ShopType", ShopType)

        val cursor: Cursor = db.rawQuery("SELECT * FROM Shop WHERE ShopName=?", arrayOf(ShopName))

        if (cursor.count > 0) {
            val result = db.update("Shop", cv, "ShopName=?", arrayOf(ShopName))
            db.close()

            return result != -1
        } else {
            db.close()

            return false
        }
    }

    fun deleteShopData(ShopName: String): Boolean {
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Shop WHERE ShopName=?", arrayOf(ShopName))

        if (cursor.count > 0) {
            val result = db.delete("Shop", "ShopName=?", arrayOf(ShopName))
            db.close()

            return result != -1
        } else {
            db.close()

            return false
        }
    }

    fun getText(): Cursor? {
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT * FROM Shop", null)

        return if (cursor.count > 0) {
            cursor
        } else {
            null
        }
    }

    fun getShopType(): Cursor? {
        val db = this.writableDatabase
        val cursor: Cursor = db.rawQuery("SELECT ShopName FROM Shop", null)

        return if (cursor.count > 0) {
            cursor
        } else {
            null
        }
    }
}