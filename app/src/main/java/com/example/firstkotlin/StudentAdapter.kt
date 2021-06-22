package com.example.jietapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.example.firstkotlin.R
import com.example.firstkotlin.Student

class StudentAdapter(private var students:ArrayList<Student>, private var context: Context):BaseAdapter()
{
    private class ViewHolder
    {
        public lateinit var profileCharTextView: TextView
        public lateinit var nameTextView: TextView
        public lateinit var rollnoTextView:TextView
        public lateinit var departmentTextView:TextView
    }

//    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
//    {
//        var viewHolder: StudentAdapter.ViewHolder
//        if(convertView==null)
//        {
//            viewHolder= StudentAdapter.ViewHolder()
//            var layoutInflator=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
//            var convertView=layoutInflator.inflate(R.layout.student_row_layout,parent,false)
//            viewHolder.profileCharTextView=convertView.findViewById(R.id.profileCharTextView)
//            viewHolder.nameTextView=convertView.findViewById(R.id.student_name_textView)
//            viewHolder.rollnoTextView=convertView.findViewById(R.id.rollnoTextView)
//            viewHolder.departmentTextView=convertView.findViewById(R.id.departmentTextView)
//            convertView.tag=viewHolder
//        }
//        else
//        {
//            viewHolder=convertView.tag as ViewHolder
//        }
//
//        var data=getItem(position) as Student
//        viewHolder.profileCharTextView.setText(data.name.get(0).toString())
//        viewHolder.nameTextView.setText(data.name)
////        viewHolder.nameTextView.typeface= ResourcesCompat.getFont(context,R.font.font_01)
//        viewHolder.rollnoTextView.setText(data.rollnumber)
//        viewHolder.departmentTextView.setText(data.department)
//        return convertView!!
//    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        var inflator=context.getSystemService(Context.LAYOUT_INFLATER_SERVICE )  as LayoutInflater
        var view= inflator.inflate(R.layout.student_row_layout,p2,false);
        var nametextview=view.findViewById<TextView>(R.id.student_name_textView)
        var rollno=view.findViewById<TextView>(R.id.rollnoTextView)
        var roundcharview=view.findViewById<TextView>(R.id.profileCharTextView)
        var deptview=view.findViewById<TextView>(R.id.departmentTextView)
//        var sem=view.findViewById<TextView>(R.id.)
        var data=getItem(p0) as Student
        nametextview.setText(data.name)
        rollno.setText(data.rollnumber)
        roundcharview.setText(data.name.get(0).toString())
        deptview.setText(data.department)
//        sem.setText(data.semester);
        return view;
    }

    override fun getItem(position: Int): Any
    {
        return students.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return students.size
    }

    fun removeRecord(studentInst: Student){
        students.remove(studentInst)
    }
}