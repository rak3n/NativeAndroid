package com.example.firstkotlin

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ContactRecyclerAdapter(private var context: Context, private var contacts: ArrayList<Contact>) : RecyclerView.Adapter<ContactRecyclerAdapter.ViewHolder>() {
    public class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var profileTextView = itemView.findViewById<TextView>(R.id.profileTextView)
        var nameTextView = itemView.findViewById<TextView>(R.id.nameTextView)
    }

    @SuppressLint("ServiceCast")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var view = inflater.inflate(R.layout.contact_row_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var contact=contacts.get(position) as Contact
        holder.nameTextView.setText(contact.name)
        holder.profileTextView.setText(contact.name[0].toString())
    }

    override fun getItemCount(): Int {
        return contacts.size
    }
}