package com.example.firstkotlin.contact_access_store

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firstkotlin.ContactRecyclerAdapter
import com.example.firstkotlin.R

class ContactAdapter(private var context: Context, contacts:ArrayList<Contact>): RecyclerView.Adapter<ContactAdapter.ViewHolder>() {
    var contacts=contacts
//    constructor(co):super()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        var display_profile = itemView.findViewById<TextView>(R.id.profileTextView)
        var display_name = itemView.findViewById<TextView>(R.id.nameTextView)
        var display_number = itemView.findViewById<TextView>(R.id.phoneNumber)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.contact_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount():Int{
        return contacts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact = contacts[position] as Contact
        holder.display_profile.setText(contact.displayName[0].toString())
        holder.display_name.setText(contact.displayName)
        holder.display_number.setText(contact.mobileNumber)
    }

}