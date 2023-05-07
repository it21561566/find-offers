
package com.example.madprojectnew

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context:Context):SQLiteOpenHelper(context,"shopdata",null,1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table shopdata(name TEXT,location TEXT,Category TEXT,description TEXT,email TEXT primary key,password TEXT)")
        p0?.execSQL("create table discountdata(startDate TEXT,endDate TEXT,discount TEXT,description TEXT primary key,email TEXT,FOREIGN KEY (email) REFERENCES shopdata (email))")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists shopdata")
        p0?.execSQL("drop table if exists discountdata")
        onCreate(p0)


    }

    fun insertdata(name:String,location: String,category:String,description:String,email: String, password:String): Boolean {
        val p0 =this.writableDatabase
        val cv =ContentValues()
        cv.put("name",name)
        cv.put("location",location)
        cv.put("category",category)
        cv.put("description",description)
        cv.put("email",email)
        cv.put("password",password)
        val result =p0.insert("shopdata",null,cv)
        if(result==-1.toLong()){
            return false
        }
        return true
    }

    fun checkuserpass(email: String,password: String): Boolean {
        val p0 = this.writableDatabase
        val query ="select * from shopdata where email ='$email' and password = '$password'"
        val cursor = p0.rawQuery(query,null)
        if (cursor.count<=0){
            cursor.close()
            return false
        }
        cursor.close()
        return true
    }

    fun savediscount(startDate:String,endDate:String,discount:String,description:String,email:String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("startDate",startDate)
        cv.put("endDate",endDate)
        cv.put("discount",discount)
        cv.put("description",description)
        cv.put("email",email)
        val result= p0.insert("discountdata",null,cv)
        if(result==-1.toLong()){
            return false
        }
        return true
    }

    fun updatedisc(startDate:String,endDate:String,discount:String,description:String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("startDate",startDate)
        cv.put("endDate",endDate)
        cv.put("discount",discount)
        val cursor:Cursor=p0.rawQuery("select startDate,endDate,discount,description from discountdata where description = ?",arrayOf(description))
        if (cursor.count>0){
            val result= p0.update("discountdata",cv,"description=?", arrayOf(description))
            return result!= -1
        }else{
            return false
        }
    }
    fun deldisc(description:String): Boolean {
        val p0 = this.writableDatabase

        val cursor:Cursor=p0.rawQuery("select * from discountdata where description = ?",arrayOf(description))
        if (cursor.count>0){
            val result= p0.delete("discountdata","description=?", arrayOf(description))
            return result!= -1
        }else{
            return false
        }
    }

    fun getdisc(): Cursor? {
        val p0=this.writableDatabase
        val cursor = p0.rawQuery("select startDate,endDate,discount,description from discountdata",null)
        return cursor

    }
}




