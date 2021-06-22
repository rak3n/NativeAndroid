package com.example.firstkotlin

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class JietDatabase(context: Context):SQLiteOpenHelper(context,"jiet_db",null,1)
{
    override fun onCreate(db: SQLiteDatabase?)
    {
        var create_table:String="CREATE TABLE STUDENT(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL " +
                ",NAME TEXT,ROLLNUMBER TEXT,SEMESTER TEXT,DEPARTMENT TEXT)"
        db?.execSQL(create_table)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int)
    {

    }

    fun addNewStudent(student: Student):Boolean
    {
        var db=writableDatabase
        var contentValues=ContentValues()
        contentValues.put("NAME",student.name)
        contentValues.put("ROLLNUMBER",student.rollnumber)
        contentValues.put("SEMESTER",student.semester)
        contentValues.put("DEPARTMENT",student.department)
        return db.insert("STUDENT",null,contentValues)>0
    }

    fun getAllStudent():ArrayList<Student>
    {
        var db=readableDatabase
        var data=ArrayList<Student>()
        var query="SELECT * FROM STUDENT";
        var cursor= db.rawQuery(query,null);

        while(cursor.moveToNext()){
            var id=cursor.getInt(cursor.getColumnIndex("ID")).toString()
            var name=cursor.getString(cursor.getColumnIndex("NAME"))
            var rollno=cursor.getString(cursor.getColumnIndex("ROLLNUMBER"))
            var sem=cursor.getString(cursor.getColumnIndex("SEMESTER"))
            var dept=cursor.getString(cursor.getColumnIndex("DEPARTMENT"))
            var student=Student(id,name,rollno,sem,dept);
            data.add(student);
        }
        return data;
    }

    fun removeStudent(id:String):Boolean
    {
        "DELETE STUDENT WHERE ID=ID_VALUE AND NAME=NAME_VALUE"
        var db=writableDatabase
        return db.delete("STUDENT","ID=?", arrayOf(id))>0
    }

    fun UpdateStudents(id: String, student: Student) :Boolean{
        var db= writableDatabase
        var contentValues=ContentValues()
        contentValues.put("NAME",student.name)
        contentValues.put("ROLLNUMBER",student.rollnumber)
        contentValues.put("SEMESTER",student.semester)
        contentValues.put("DEPARTMENT",student.department)
        return db.update("STUDENT", contentValues, "ID=?", arrayOf(id))>0
    }
}