package com.example.firstkotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class recycler_view_Activity : AppCompatActivity() {
    private lateinit var contactListView : RecyclerView
    private lateinit var contacts : ArrayList<Contact>
    private lateinit var adapter : ContactRecyclerAdapter
    private lateinit var layoutManager : RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler_view)
        init()
        initData()
        adapter = ContactRecyclerAdapter(this, contacts)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        contactListView.layoutManager = layoutManager
        contactListView.adapter=adapter

//        contactListView.setOnItemClickListener()
    }

    fun initData()
    {
        contacts= ArrayList()
        contacts.add(Contact("Virendra Soni"))
        contacts.add(Contact("Ravi Sharma"))
        contacts.add(Contact("Danish Sekh"))
        contacts.add(Contact("Kajal Soni"))
        contacts.add(Contact("Hemant Purohit"))
        contacts.add(Contact("Geeta Choudhary"))
        contacts.add(Contact("Shanti Soni"))
        contacts.add(Contact("Bhavesh Mathur"))
    }

    fun init()
    {
        contactListView = findViewById(R.id.contactList)
    }
}