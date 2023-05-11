package com.example.banking_and_finance.data

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
//import com.example.android_kotlin_bank_offers_app.model.*
import com.example.banking_and_finance.model.*
import java.text.DateFormat
import java.util.*

class BankDatabaseHandler(context: Context):
        SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        // use SQL to create table
        var CREATE_BANK_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + KEY_ID + " INTEGER PRIMARY KEY,"+
                KEY_BANK_NAME + " TEXT," +
                KEY_BANK_ASSIGNED_BY + " TEXT," +
                KEY_BANK_ASSIGNED_TO + " TEXT," +
                KEY_BANK_ASSIGNED_TIME + " LONG" +");"
        db?.execSQL(CREATE_BANK_TABLE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

        db?.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // create a new table after deleting the previous using above function
        onCreate(db)
    }

    /* CRUD = create read update delete */

    fun createJob(bank: bank) {

        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues()
        values.put(KEY_BANK_NAME, bank.bankName)
        values.put(KEY_BANK_ASSIGNED_BY, bank.assignedBy)
        values.put(KEY_BANK_ASSIGNED_TO, bank.assignedTo)
        values.put(KEY_BANK_ASSIGNED_TIME, System.currentTimeMillis())

        db.insert(TABLE_NAME, null, values)

        Log.d("DEBUG", "SUCCESS")
        db.close()
    }

    @SuppressLint("Range")
    fun readAJob(id: Int): bank {

        var db: SQLiteDatabase = writableDatabase
        var cursor: Cursor = db.query(
            TABLE_NAME, arrayOf(
                KEY_ID, KEY_BANK_NAME, KEY_BANK_ASSIGNED_BY,
                KEY_BANK_ASSIGNED_TO, KEY_BANK_ASSIGNED_TIME
            ), "$KEY_ID=?", arrayOf(id.toString()),
            null, null, null, null
        )

        if (cursor != null)
            cursor.moveToFirst()

        var bank= bank()

        bank.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
        bank.bankName = cursor.getString(cursor.getColumnIndex(KEY_BANK_NAME))
        bank.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_BANK_ASSIGNED_BY))
        bank.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_BANK_ASSIGNED_TO))
        bank.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_BANK_ASSIGNED_TIME))

        var dateFormat: DateFormat = DateFormat.getDateInstance()
        var formatteddate = dateFormat.format(
            Date(
                cursor.getLong
                    (cursor.getColumnIndex(KEY_BANK_ASSIGNED_TIME))
            ).time
        )

        return bank

    }

    @SuppressLint("Range")
    fun readBanks(): ArrayList<bank> {


        var db: SQLiteDatabase = readableDatabase
        var list: ArrayList<bank> = ArrayList()

        //Select all chores from table
        var selectAll = "SELECT * FROM $TABLE_NAME"

        var cursor: Cursor = db.rawQuery(selectAll, null)

        //loop through our chores
        if (cursor.moveToFirst()) {
            do {
                var bank = bank()

                bank.id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                bank.bankName = cursor.getString(cursor.getColumnIndex(KEY_BANK_NAME))
                bank.assignedTo = cursor.getString(cursor.getColumnIndex(KEY_BANK_ASSIGNED_TO))
                bank.timeAssigned = cursor.getLong(cursor.getColumnIndex(KEY_BANK_ASSIGNED_TIME))
                bank.assignedBy = cursor.getString(cursor.getColumnIndex(KEY_BANK_ASSIGNED_BY))

                list.add(bank)

            }while (cursor.moveToNext())
        }


        return list

    }

    fun updatejob(bank: bank): Int {
        var db: SQLiteDatabase = writableDatabase

        var values: ContentValues = ContentValues()
        values.put(KEY_BANK_NAME, bank.bankName)
        values.put(KEY_BANK_ASSIGNED_BY, bank.assignedBy)
        values.put(KEY_BANK_ASSIGNED_TO, bank.assignedTo)
        values.put(KEY_BANK_ASSIGNED_TIME, System.currentTimeMillis())

        //update a row
        return db.update(TABLE_NAME, values, "$KEY_ID=?", arrayOf(bank.id.toString()))
    }

    fun deletejob(id: Int) {
        var db: SQLiteDatabase = writableDatabase
        db.delete(TABLE_NAME, "$KEY_ID=?", arrayOf(id.toString()))

        db.close()
    }

    fun getJobsCount(): Int {
        var db: SQLiteDatabase = readableDatabase
        var countQuery = "SELECT * FROM $TABLE_NAME"
        var cursor: Cursor = db.rawQuery(countQuery, null)

        return cursor.count
    }
}