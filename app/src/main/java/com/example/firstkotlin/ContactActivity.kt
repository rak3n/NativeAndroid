package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ListView

class ContactActivity : AppCompatActivity() {
    private lateinit var contactListView:ListView
    private lateinit var contacts:ArrayList<Contact>
    private lateinit var adapter:ContactAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
        init()
        initData()
        adapter=ContactAdapter(contacts,this)
        contactListView.adapter=adapter

        contactListView.setOnItemClickListener(AdapterView.OnItemClickListener(){
            adapterView: AdapterView<*>, view1: View, i: Int, l:Long ->
            var position=adapterView.selectedItemPosition
            var  contact = contacts[i]
            var bundle=Bundle()
            print(contact.name)
            bundle.putString("name", contact.name)

            var internt = Intent(this, DetailsActivity::class.java)
            internt.putExtras(bundle)
            startActivity(internt)
        })
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
        contactListView=findViewById(R.id.contactList)
    }
}