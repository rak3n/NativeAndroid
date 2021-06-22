package com.example.firstkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.firstkotlin.Fragments.SignUpFragment

class UserDataBase(context: Context): SQLiteOpenHelper(context,"user_db", null,1, ){
    override fun onCreate(db: SQLiteDatabase?) {
        var create_table:String="CREATE TABLE USERS(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
                ", USERNAME TEXT, PASSWORD TEXT, MOBILE TEXT)"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("Not yet implemented")
    }

    fun checkUserExist(userName: CharSequence?):Boolean{
        var qry= "SELECT * FROM USERS WHERE USERNAME='${userName.toString()}'"
        var db=readableDatabase;
        var cur = db.rawQuery(qry, null)
        var count=false
        while(cur.moveToNext()){
            count=true
            break
        }
        return count
    }

    fun addUser(user : UserPOJO):Boolean {
        var db=writableDatabase
        var contentValues= ContentValues()
        contentValues.put("USERNAME",user.username)
        contentValues.put("PASSWORD",user.password)
        contentValues.put("MOBILE",user.mobile)
        return db.insert("USERS",null,contentValues)>0
    }

    fun getUserData(username:String):UserPOJO{
        var qry="SELECT * FROM USERS WHERE USERNAME='$username'"
        var db=readableDatabase;
        var users=ArrayList<UserPOJO>()
        var cur = db.rawQuery(qry, null)
        while(cur.moveToNext()){
            var username = cur.getString(cur.getColumnIndex("USERNAME")).toString()
            var password = cur.getString(cur.getColumnIndex("PASSWORD")).toString()
            var phone = cur.getString(cur.getColumnIndex("MOBILE")).toString()
            users.add(UserPOJO(username, password, phone))
        }

        return users[0]
    }

}