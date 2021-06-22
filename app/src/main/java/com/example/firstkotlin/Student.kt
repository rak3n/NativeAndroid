package com.example.firstkotlin

class Student {
    public lateinit var id:String
    public lateinit var name:String
    public lateinit var rollnumber:String
    public lateinit var semester:String
    public lateinit var department:String

    public constructor(id:String,name:String,rollnumber:String,semester:String,department:String)
    {
        this.id=id
        this.name=name
        this.rollnumber=rollnumber
        this.department=department
        this.semester=semester
    }

    public constructor(name:String,rollnumber:String,semester:String,department:String)
    {
        this.name=name
        this.rollnumber=rollnumber
        this.department=department
        this.semester=semester
    }
}