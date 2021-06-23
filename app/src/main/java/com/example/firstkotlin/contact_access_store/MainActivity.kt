package com.example.firstkotlin.contact_access_store

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.ContactRecyclerAdapter
import com.example.firstkotlin.R

class MainActivity : AppCompatActivity() {
    private lateinit var data:ArrayList<Contact>
    lateinit var contactList: RecyclerView
    private lateinit var adapter : ContactAdapter
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        init()
        initData()
        adapter = ContactAdapter(this, data)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactList.layoutManager = layoutManager
        contactList.adapter=adapter
    }

    fun initData(){
        data=ArrayList()
        var resolver=contentResolver
        var cursor=resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC")
        while(cursor!!.moveToNext())
        {
            var mobile=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
            var name=cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
            var contact=Contact(mobile,name)
            data.add(contact)
        }
    }

    fun init(){
        contactList=findViewById(R.id.contactList)
    }
}