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
import com.example.firstkotlin.UserPOJO

class SignUpFragment(UserDB: UserDataBase, preference: SharedPreferences.Editor) : Fragment(), TextWatcher, View.OnClickListener {
    
    private lateinit var username: EditText
    private lateinit var phone:EditText
    private lateinit var password:EditText
    private lateinit var submit: Button
    private lateinit var v:View
    private var db:UserDataBase = UserDB
    var allowSave=true
    private var editor=preference

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        v=inflater.inflate(R.layout.fragment_sign_up, container, false)
        init()
        username.addTextChangedListener(this)
        submit = v.findViewById<Button>(R.id.submitBtn)
        submit.setOnClickListener(this)
        return v
    }
    
    fun init(){
        username=v.findViewById(R.id.editUsername)
        password=v.findViewById(R.id.editPassword)
        phone=v.findViewById(R.id.editNumber)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        if(db.checkUserExist(s)){
            if(s.toString().length>3){
                username.error = "User Already Exist"
            }
            allowSave=false
        }else{
            allowSave=true
        }
    }

    override fun afterTextChanged(s: Editable?) {

    }

    override fun onClick(v: View?) {
        var usernameText=username.text.toString()
        var passText = password.text.toString()
        var mobileText = phone.text.toString()
        if(usernameText.isEmpty() || passText.isEmpty() || mobileText.isEmpty()){
            Toast.makeText(requireContext(),"All Fields are Required",Toast.LENGTH_SHORT).show()
        }else{
            var user=UserPOJO(usernameText, passText, mobileText)
            if(allowSave){
                if(db.addUser(user) && allowSave){
                    Toast.makeText(requireContext(), "User Saved !!!", Toast.LENGTH_SHORT).show()
                    editor.putString("login","1").apply()
                    username.setText("")
                    password.setText("")
                    phone.setText("")
                }else{
                    Toast.makeText(requireContext(), "User Not Saved !!!", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(requireContext(), "User Already Exist", Toast.LENGTH_SHORT).show()
            }
        }
    }
}