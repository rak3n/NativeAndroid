package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*

class UpdateActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var nameEdit: EditText
    private lateinit var rollEdit: EditText
    private lateinit var semSpinner: Spinner
    private lateinit var departmentSpinner: Spinner
    private lateinit var updateRecordBtn: Button
    private lateinit var departmentAdapter: ArrayAdapter<String>
    private lateinit var semseterAdapter: ArrayAdapter<String>
    private lateinit var database:JietDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)
        init()
        loadDataIntoSpinner()
        fillStudentData()

        updateRecordBtn.setOnClickListener(this)
    }

    fun fillStudentData(){
        var id=intent.getStringExtra("ID")
        var name=intent.getStringExtra("NAME")
        var dept=intent.getStringExtra("DEPT")
        var sem=intent.getStringExtra("SEM")
        var roll=intent.getStringExtra("ROLLNO")

        nameEdit.setText(name)
        rollEdit.setText(roll)
        semSpinner.setSelection(semseterAdapter.getPosition(sem))
        departmentSpinner.setSelection(departmentAdapter.getPosition(dept))
    }

    fun init()
    {
        nameEdit=findViewById(R.id.nameEdit)
        rollEdit=findViewById(R.id.rollnoEditText)
        semSpinner=findViewById(R.id.semSpinner)
        departmentSpinner=findViewById(R.id.departmentSpinner)
        updateRecordBtn=findViewById(R.id.updateRecordBtn)
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

    override fun onClick(v: View?) {
        var name = nameEdit.text.toString()
        var rollno = rollEdit.text.toString()
        var department = departmentSpinner.selectedItem as String
        var semseter = semSpinner.selectedItem as String
        var id = intent.getStringExtra("ID")
//        if(name.isEmpty())
//        {
//            nameEdit.setError("Fill Student Name")
//            return this
//        }
//
//        if(rollno.isEmpty())
//        {
//            rollEdit.setError("Fill Student RollNumber")
//            return this
//        }
        database = JietDatabase(this)
        var student = Student(name, rollno, semseter, department)
        if (database.UpdateStudents(id.toString(), student)) {
            var toast=Toast.makeText(this, "Record Added Successfully", Toast.LENGTH_LONG)
            toast.setGravity(Gravity.CENTER,0,0)
            toast.show()
            var intent = Intent(this, StudentActivity::class.java)
            startActivity(intent)
//            var dialog = AddDialogFragment()
//            dialog.show(supportFragmentManager, "dialog")
        } else
            Toast.makeText(this, "Sorry Record Not Added", Toast.LENGTH_LONG).show()
    }

}