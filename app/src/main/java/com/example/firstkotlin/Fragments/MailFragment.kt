package com.example.firstkotlin.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.firstkotlin.R

class MailFragment(_title: String) : Fragment() {
    private lateinit var title:String

    init{
        title=_title
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        var obj=view?.findViewById<TextView>(R.id.textView)
//        obj?.setText(title)
        var inf = inflater.inflate(R.layout.fragment_mail, container, false)
        var textBox = inf.findViewById<TextView>(R.id.textView)
        textBox.setText(title)
        return inf
    }
}