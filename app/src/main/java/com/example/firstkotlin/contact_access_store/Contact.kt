package com.example.firstkotlin.contact_access_store

class Contact {
    lateinit var mobileNumber:String
    lateinit var displayName:String

    constructor(mobileNumber:String, name:String){
        this.mobileNumber=mobileNumber
        this.displayName=name
    }
}