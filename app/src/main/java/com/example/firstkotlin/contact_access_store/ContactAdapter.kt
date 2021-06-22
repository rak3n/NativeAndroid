package com.example.firstkotlin.contact_access_store

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class ContactAdapter: RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    constructor():super()

    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) : ViewHolder{

    }

    override fun getItemCount():Int{
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

}