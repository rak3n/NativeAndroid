package com.example.firstkotlin.dialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import com.example.firstkotlin.R

class DialogActivityActivity : AppCompatActivity(), Communication {

    private lateinit var confirmBtn:Button
    private lateinit var infoBtn:Button
    private lateinit var singalChoiceBtn:Button
    private lateinit var multiChoiceBtn:Button
    private lateinit var customBtn:Button
    private lateinit var datePickerBtn:Button



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dialog_activity)
        init()
        listeners()
    }

    fun init(){
        confirmBtn = findViewById(R.id.confirmBtn)
        infoBtn = findViewById(R.id.infoBtn)
        singalChoiceBtn = findViewById(R.id.singalChoice)
        multiChoiceBtn = findViewById(R.id.mutliChoice)
        customBtn =findViewById(R.id.customBtn)
        datePickerBtn = findViewById(R.id.datePicker)
    }

    private fun listeners(){
        confirmBtn.setOnClickListener(){
            var confirmDialog = ConfirmDialogFragment()
            confirmDialog.show(supportFragmentManager, "confirmDialog")
        }
        infoBtn.setOnClickListener(){
            var infoDialog = InfoDialogFragment()
            infoDialog!!.show(supportFragmentManager, "infoDialog")
        }
        singalChoiceBtn.setOnClickListener(){
            var singalChoice = SingalChoiceDialogFragment()
            singalChoice.show(supportFragmentManager, "singalChoice")
        }
        multiChoiceBtn.setOnClickListener(){
            var multiChoice = MultiChoiceDialogFragment()
            multiChoice.show(supportFragmentManager, "multiChoice")
        }
        customBtn.setOnClickListener(){
            var customDialog = CustomDialogFragment()
            customDialog.show(supportFragmentManager, "customDialog")
        }
        datePickerBtn.setOnClickListener(){}
    }

    override fun onBackPressed() {
//        super.onBackPressed()
        var confirmDialog = ConfirmDialogFragment()
        confirmDialog.show(supportFragmentManager, "confirmDialog")
    }

    override fun getName(name: String) {
        var toast=Toast.makeText(this, "we get $name", Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER,0,0)
        toast.show()
    }
}