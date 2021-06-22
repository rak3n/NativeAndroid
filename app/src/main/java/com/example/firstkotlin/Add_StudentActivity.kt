package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class Add_StudentActivity : AppCompatActivity()
{
    private lateinit var nameEdit:EditText
    private lateinit var rollEdit:EditText
    private lateinit var semSpinner:Spinner
    private lateinit var departmentSpinner:Spinner
    private lateinit var saveRecordBtn:Button
    private lateinit var departmentAdapter:ArrayAdapter<String>
    private lateinit var semseterAdapter:ArrayAdapter<String>
    private lateinit var database:JietDatabase

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add__student)
        init()
        loadDataIntoSpinner()
        saveRecordBtn.setOnClickListener()
        {
            var name=nameEdit.text.toString()
            var rollno=rollEdit.text.toString()
            var department=departmentSpinner.selectedItem as String
            var semseter=semSpinner.selectedItem as String
            if(name.isEmpty())
            {
                nameEdit.setError("Fill Student Name")
                return@setOnClickListener
            }

            if(rollno.isEmpty())
            {
                rollEdit.setError("Fill Student RollNumber")
                return@setOnClickListener
            }
            database= JietDatabase(applicationContext)
            var student=Student(name,rollno,semseter,department)
            if(database.addNewStudent(student))
            {
//                Toast.makeText(applicationContext, "Record Added Successfully", Toast.LENGTH_LONG).show()
//                var intent= Intent(this,StudentActivity::class.java)
//                startActivity(intent)
                var dialog = AddDialogFragment()
                dialog.show(supportFragmentManager, "dialog")
            }
            else
                Toast.makeText(applicationContext,"Sorry Record Not Added",Toast.LENGTH_LONG).show()
        }
    }
    fun init()
    {
        nameEdit=findViewById(R.id.nameEdit)
        rollEdit=findViewById(R.id.rollnoEditText)
        semSpinner=findViewById(R.id.semSpinner)
        departmentSpinner=findViewById(R.id.departmentSpinner)
        saveRecordBtn=findViewById(R.id.saveRecordBtn)
    }

    fun loadDataIntoSpinner()
    {
        var departmentData=resources.getStringArray(R.array.department)
        var semesterData=resources.getStringArray(R.array.semester)
        departmentAdapter=ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,departmentData)
        semseterAdapter= ArrayAdapter(this,android.R.layout.simple_spinner_dropdown_item,semesterData)
        departmentSpinner.adapter=departmentAdapter
        semSpinner.adapter=semseterAdapter
    }
}