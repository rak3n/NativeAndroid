package com.example.firstkotlin

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

public class ContactAdapter(private var contacts:ArrayList<Contact>,private var context:Context): BaseAdapter()
{
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        var layoutInflator=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        var convertView=layoutInflator.inflate(R.layout.contact_row_layout,parent,false)
        var profileTextView=convertView.findViewById<TextView>(R.id.profileTextView)
        var nameTextView=convertView.findViewById<TextView>(R.id.nameTextView)
        var data=getItem(position) as Contact
        profileTextView.setText(data.name.get(0).toString())
        nameTextView.setText(data.name)
//        nameTextView.typeface= ResourcesCompat.getFont(context,R.font.font_01)
        return convertView
    }

    override fun getItem(position: Int): Any
    {
        return contacts.get(position)
    }

    override fun getItemId(position: Int): Long
    {
        return position.toLong()
    }

    override fun getCount(): Int
    {
        return contacts.size
    }
}