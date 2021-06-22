package com.example.firstkotlin.Fragments

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.firstkotlin.R
import com.example.firstkotlin.UserDataBase

class LoginFragment(UserDB: UserDataBase, editor: SharedPreferences.Editor) : Fragment(), View.OnClickListener, TextWatcher {

    private lateinit var username: EditText
    private lateinit var password: EditText
    private lateinit var submitBtn: Button
    private var db:UserDataBase=UserDB
    private var editor=editor

    var allowSave=false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var inf=inflater.inflate(R.layout.fragment_login, container, false)
        init(inf)
        username.addTextChangedListener(this)
        submitBtn.setOnClickListener(this)
        return inf
    }

    fun init(v: View){
        username = v.findViewById<EditText>(R.id.editUsername)
        password = v.findViewById<EditText>(R.id.editPassword)
        submitBtn = v.findViewById<Button>(R.id.submitBtn)
    }

    override fun onClick(v: View?) {

        if(allowSave){
            var data = db.getUserData(username.text.toString())
            if(data.password == password.text.toString()){
                Toast.makeText(requireContext(),"Login Successful.....", Toast.LENGTH_SHORT).show()
                editor.putString("login","1").apply()
            }else{
                Toast.makeText(requireContext(),"Incorrect Details", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(requireContext(),"Username is Invalid", Toast.LENGTH_SHORT).show()
        }
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(!db.checkUserExist(s)){
            allowSave=false
            if(s.toString().length>4) {
                username.error = "User Not Found"
            }
        }else{
            allowSave=true
        }
    }

    override fun afterTextChanged(s: Editable?) {
    }
}