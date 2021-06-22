package com.example.firstkotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ListView
import android.widget.PopupMenu
import android.widget.Toast
import com.example.jietapp.StudentAdapter

class StudentActivity : AppCompatActivity(), AdapterView.OnItemClickListener,
    AdapterView.OnItemLongClickListener {
    private lateinit var studentListView: ListView
    private lateinit var students:ArrayList<Student>
    private lateinit var adapter:StudentAdapter

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student)
        init()
        initData()
        if(students.size==0)
        {
            Toast.makeText(this,"No Record Found",Toast.LENGTH_LONG).show()
        }
        adapter= StudentAdapter(students,this)
        studentListView.adapter=adapter
//        adapter.notifyDataSetChanged()
        studentListView.onItemLongClickListener = this
    }

    fun init()
    {
        studentListView=findViewById(R.id.studentListView)
    }

    fun initData()
    {
        var database=JietDatabase(this)
        students=database.getAllStudent()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean
    {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean
    {
        return when(item.itemId)
        {
            R.id.add->
            {
                var intent= Intent(this,Add_StudentActivity::class.java)
                startActivity(intent)
                true
            }
            R.id.exit->
            {
                finish()
                true
            }
            else ->super.onOptionsItemSelected(item)
        }
    }

    override fun onItemLongClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long): Boolean {
        var popup_menu = PopupMenu(this, view!!)
        var menuInflater = popup_menu.menuInflater
        menuInflater.inflate(R.menu.popup_menu, popup_menu.menu)
//        print("HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH")
        popup_menu.show()
        popup_menu.setOnMenuItemClickListener(object: android.widget.PopupMenu.OnMenuItemClickListener{

            override fun onMenuItemClick(item: MenuItem?): Boolean {
                return when(item?.itemId){
                    R.id.deleteRecord->{
                        var student = students[position]
                        var database=JietDatabase(applicationContext)
                        if(database.removeStudent(student.id)){
                            adapter.removeRecord(student)
                            adapter.notifyDataSetChanged()
                            Toast.makeText(applicationContext, "Record Deleted Successfully", Toast.LENGTH_LONG).show()
                        }
                        true
                    }
                    R.id.updateRecord->{
                        var student = students[position]
                        var intent = Intent(this@StudentActivity,UpdateActivity::class.java)
                        intent?.putExtra("ID", student.id)
                        intent?.putExtra("NAME", student.name)
                        intent?.putExtra("ROLLNO", student.rollnumber)
                        intent?.putExtra("DEPT", student.department)
                        intent?.putExtra("SEM", student.semester)

                        startActivity(intent)
                        true
                    }
                    else -> true
                }
            }
        })
        return true
    }

    override fun onItemClick(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
    }
}

